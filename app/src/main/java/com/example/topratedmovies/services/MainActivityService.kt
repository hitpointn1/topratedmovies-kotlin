package com.example.topratedmovies.services


class MainActivityService : BaseService() {

    override var ApiUrl: String = "https://api.themoviedb.org/3/movie/top_rated"
    override var ApiLanguage: String = "language=en-US"

    fun GetTopRatedFilms(onSuccess: (text: String) -> Unit, onTimeout: () -> Unit) {
        val params = arrayListOf<String>("page=1")
        CallApi(params, onSuccess, onTimeout)
    }

}