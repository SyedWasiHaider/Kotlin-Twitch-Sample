package com.wasi.twitching.Extensions

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Created by Wasi on 3/13/2016.
 */
fun String.AddUrlParams(params : HashMap<String, String>) : String{
    val querySign = if (this.last() == '?') "" else "?"
    var str = this
    str = this.plus(querySign)
    params.entries.forEach { set ->
        str = str.plus(set.key).plus("=").plus(set.value).plus("&")
    }
    str.removeRange(str.length - 1, str.length-1)
    return str
}

fun String.FromAssets(context : Context) : String {
    val reader = BufferedReader(InputStreamReader(context.getAssets().open(this)));

    // do reading, usually loop until end of file reading
    var sb = StringBuilder();
    var mLine = reader.readLine();
    while (mLine != null) {
        sb.append(mLine); // process line
        mLine = reader.readLine();
    }
    reader.close();
    return sb.toString();
}