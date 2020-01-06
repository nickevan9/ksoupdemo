package com.fcannizzaro.ksoup.sample.model

import com.fcannizzaro.ksoup.IKsoup
import com.fcannizzaro.ksoup.delegates.*

/**
 * Created by Francesco Cannizzaro (fcannizzaro).
 */

class Package : IKsoup(".story-item") {

    class Info : IKsoup(".more-info") {


    }

    val name by bindText("h3")

    val link by bindLink("h3 a")

//    val description by bindText("p.type-ellipsis")

    val icon by bindImage(".story-cover")

    val info: Info by bindClass(Info(), this)


}