package com.wasi.twitching.API

import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wasi.twitching.Constants

/**
 * Created by Wasi on 3/13/2016.
 */
object  GameInfoService {
    val TAG = GameInfoService::class.java.name

    val GAME_API_URL = "http://www.giantbomb.com/api/game/%s?api_key=".plus(Constants.GB_KEY).plus("&format=json")
    fun getProductData(gb_game_id : String, onSuccess: (JsonObject) -> Unit){
        if (gb_game_id.equals("0")){
            Log.w(TAG, "invalid gbid provided in getproductdata")
            return
        }
        String.format(GAME_API_URL, gb_game_id)
                .httpGet()
                .responseString { request, response, result ->
                    //do something with response
                    when (result) {
                        is Result.Failure -> {
                            val error = result.error.toString()
                            Log.e(TAG, error)
                        }
                        is Result.Success -> {
                            val parser = JsonParser();
                            onSuccess(parser.parse(result.value).asJsonObject)
                        }
                    }

                }

    }
}
