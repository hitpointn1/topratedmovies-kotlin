package com.example.topratedmovies.services

import okhttp3.*


class IMDBService {
    fun GetTopRatedFilms(callback: Callback) {
        var url = "https://api.themoviedb.org/3/movie/top_rated"
        var params = arrayListOf<String>("language=en-US", "page=1", "api_key=*")
        var endpoint = url + params.joinToString( separator = "&", prefix = "?");
        var request = Request.Builder().url(endpoint).build()

        var client = OkHttpClient()
        client.newCall(request).enqueue(callback)
    }
}