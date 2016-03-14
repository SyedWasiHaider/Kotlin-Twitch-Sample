package com.wasi.twitching.Views.Activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.google.gson.JsonParseException
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import com.wasi.twitching.API.GameInfoService
import com.wasi.twitching.Extensions.FromAssets
import com.wasi.twitching.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    val TAG = DetailActivity::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        postponeEnterTransition()

        val url = intent.getStringExtra("artLargeUrl")
        val gameName = intent.getStringExtra("name")
        val viewers = intent.getIntExtra("viewers",0)
        val gbId = intent.getIntExtra("gbid",0).toString()
        detail_tv_title.text = gameName
        detail_tv_views.text = "${viewers} viewers"
        GameInfoService.getProductData(gbId, {data ->
            try {
                detail_tv_moreinfo.text = data.getAsJsonObject("results").getAsJsonPrimitive("deck").asString
            }catch (exception : Exception){
                Log.e(TAG, exception.message)
            }
        })

        Picasso.with(detail_imageview_game.context)
                .load(url)
                .into(detail_imageview_game, object : Callback {
                    override fun onSuccess() {
                        startPostponedEnterTransition()
                    }

                    override fun onError() {
                        startPostponedEnterTransition()
                    }
                })

    }

}
