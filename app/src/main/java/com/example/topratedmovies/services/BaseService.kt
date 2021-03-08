package com.example.topratedmovies.services

import android.util.Log
import okhttp3.*
import java.io.IOException

abstract class BaseService {
    protected abstract var ApiUrl: String
    protected var ApiKey: String = "**"
    protected abstract var ApiLanguage: String

    protected fun CallApi(params: ArrayList<String>, onSuccess: (text: String) -> Any, getId: Long? = null) {
        params.add("api_key=$ApiKey")
        params.add(ApiLanguage)
        if (getId != null){
            ApiUrl+= getId
        }
        val endpoint = ApiUrl + params.joinToString( separator = "&", prefix = "?")
        val request = Request.Builder().url(endpoint).build()

        val client: OkHttpClient = OkHttpClient()
                .newBuilder()
                .build()
        client.newCall(request).enqueue(callbackHandler(onSuccess))
    }



    private fun callbackHandler(onSuccess: (text: String) -> Any): Callback {
        return object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("MyLog", "Failure " + e.message)
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