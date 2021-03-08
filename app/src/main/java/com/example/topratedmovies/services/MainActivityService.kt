package com.example.topratedmovies.services


class MainActivityService : BaseService() {
    override var ApiUrl: String = "https://api.themoviedb.org/3/movie/top_rated"

    fun GetTopRatedFilms(onSuccess: (text: String) -> Unit) {
        val params = arrayListOf<String>("language=en-US", "page=1")
        CallApi(params, onSuccess)
    }

}