package com.wasi.twitching.API

import android.widget.ArrayAdapter
import android.widget.ListView
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wasi.twitching.Data.TwitchGameModel
import com.wasi.twitching.Extensions.AddUrlParams
import java.util.*

/**
 * Created by Wasi on 3/11/2016.
 */
object TwitchAPIService {

    val TOP_GAMES_URL = "https://api.twitch.tv/kraken/games/top"
    fun GetTopGameStreams(onSuccess: (result: MutableList<TwitchGameModel>) -> Unit, onFailure: (errorMessage: String) -> Unit = { err->Unit}, Limit : Int = 100, Offset : Int = 0) {
        val params : HashMap<String, String> = HashMap()
        params["limit"] = Limit.toString()
        params["offset"] = Offset.toString()
        var urlRequest = String(TOP_GAMES_URL.toCharArray())
        urlRequest.AddUrlParams(params).
                httpGet().
                responseString { request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    val error = result.error.toString()
                    onFailure(error)
                }
                is Result.Success -> {
                    val parser = JsonParser();
                    val list = parser.parse(result.value).asJsonObject["top"] as JsonArray
                    val result : MutableList<TwitchGameModel> = arrayListOf()
                    list.forEach { element-> result.add(TwitchGameModel(element as JsonObject))  }
                    onSuccess(result)
                }
            }

        }

    }
}