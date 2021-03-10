package com.example.topratedmovies.services

import android.util.Log
import okhttp3.*
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

abstract class BaseService {
    protected abstract var ApiUrl: String
    protected var ApiKey: String = "**"
    protected abstract var ApiLanguage: String

    protected fun CallApi(params: ArrayList<String>, onSuccess: (text: String) -> Any, onTimeout: () -> Unit, getId: Long? = null) {
        params.add("api_key=$ApiKey")
        params.add(ApiLanguage)
        if (getId != null){
            ApiUrl+= getId
        }
        val endpoint = ApiUrl + params.joinToString( separator = "&", prefix = "?")
        val request = Request.Builder().url(endpoint).build()

        val client: OkHttpClient = OkHttpClient()
                .newBuilder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build()
        client.newCall(request).enqueue(callbackHandler(onSuccess, onTimeout))
    }



    private fun callbackHandler(onSuccess: (text: String) -> Any, onTimeout: () -> Unit): Callback {
        return object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("MyLog", "Failure " + e.message)
                if (e is SocketTimeoutException) {
                    onTimeout()
                    return
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val bodyText = response.body?.string()
                if (bodyText == null) {
                    onFailure(call, IOException())
                    return
                }
                onSuccess(bodyText)
            }
        }
    }
}