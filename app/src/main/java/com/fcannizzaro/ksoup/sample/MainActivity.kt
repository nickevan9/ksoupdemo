package com.fcannizzaro.ksoup.sample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import coil.api.load
import com.fcannizzaro.ksoup.Ksoup
import com.fcannizzaro.ksoup.ksoup.sample.R
import com.fcannizzaro.ksoup.sample.model.NPM
import com.fcannizzaro.ksoup.sample.model.Package
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.idik.lib.slimadapter.SlimAdapter
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {

    private val list by lazy { findViewById<RecyclerView>(R.id.list) }
    private lateinit var mAdapter: CommicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = CommicAdapter(this)
        list.layoutManager = LinearLayoutManager(this)
        list.setHasFixedSize(true)
        list.adapter = mAdapter

        CoroutineScope(Dispatchers.IO).launch {

            val document = Jsoup.connect("https://truyenqq.com/").get()
            val ksoup = Ksoup(document)

            val npm: NPM = ksoup.from(NPM())

            // or
            // val packages = ksoup.fromList<Package>(Package())

            withContext(Dispatchers.Main) {
                title = npm.title
                mAdapter.updateData(npm.packages)
            }

        }

    }


}
