package com.fcannizzaro.ksoup.sample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.fcannizzaro.ksoup.ksoup.sample.R
import com.fcannizzaro.ksoup.sample.model.Package
import kotlin.properties.Delegates

class CommicAdapter(private val context: Context) : RecyclerView.Adapter<CommicAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    private var mData: List<Package> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_comic, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(mData[position])
    }

    fun updateData(newData: List<Package>) {
        mData = newData
    }


    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mImgComic: ImageView = itemView.findViewById(R.id.img_comic)
        private val mTvName: TextView = itemView.findViewById(R.id.tv_name)
        private val mTvStatus: TextView = itemView.findViewById(R.id.tv_status)
        private val mTvView: TextView = itemView.findViewById(R.id.tv_view)
        private val mTvFollow: TextView = itemView.findViewById(R.id.tv_follow)

        fun bindView(item: Package) {
            mImgComic.load(item.icon) {
                placeholder(R.drawable.box)
                error(R.drawable.ic_launcher_background)
            }

            mTvName.text = item.name
            mTvStatus.text = item.info.element!!.getElementsByClass("info")[0].text()
            mTvView.text = item.info.element!!.getElementsByClass("info")[1].text()
            mTvFollow.text = item.info.element!!.getElementsByClass("info")[2].text()
        }
    }
}