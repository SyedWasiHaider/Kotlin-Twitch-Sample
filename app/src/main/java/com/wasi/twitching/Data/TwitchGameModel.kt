package com.wasi.twitching.Data

import android.content.Intent
import android.os.Parcelable
import com.github.salomonbrys.kotson.byInt
import com.github.salomonbrys.kotson.byString
import com.github.salomonbrys.kotson.get
import com.google.gson.JsonObject
import java.io.Serializable

/**
 * Created by Wasi on 3/13/2016.
 */

class TwitchGameModel(val obj: JsonObject) {
    val channels: Int by obj.byInt  // Maps to obj["channels"]
    val viewers: Int by obj.byInt
    val name: String by obj["game"].byString("name")// Maps to obj["dates"][0]
    val gbid: Int by obj["game"].byInt("giantbomb_id") // Maps to obj["dates"][0]
    val artLargeUrl : String by obj["game"]["box"].byString("large")
    val artMediumUrl : String by obj["game"]["box"].byString("medium")
    val artSmallUrl : String by obj["game"]["box"].byString("small")
    fun putToIntent(intent : Intent){
        intent.putExtra("channels", channels)
        intent.putExtra("viewers", viewers)
        intent.putExtra("name", name)
        intent.putExtra("gbid", gbid)
        intent.putExtra("artLargeUrl", artLargeUrl)
        intent.putExtra("artMediumUrl", artMediumUrl)
        intent.putExtra("artSmallUrl", artSmallUrl)
    }
}