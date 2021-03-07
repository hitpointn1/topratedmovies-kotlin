package com.example.topratedmovies.services

import com.example.topratedmovies.models.Movie
import okhttp3.OkHttpClient


class IMBDService {
    fun GetTopRatedFilms() {
        var url = "https://api.themoviedb.org/3/movie/top_rated"
        var params = arrayListOf<String>("language=en-US", "page=1", "api_key=*")
        var endpoint = url + params.joinToString( separator = "&", prefix = "?");

        var client = OkHttpClient()
    }
}