package com.example.topratedmovies.services

class DetailsActivityService : BaseService() {

    override var ApiUrl: String = "https://api.themoviedb.org/3/movie/"
    override var ApiLanguage: String = "language=en-US"

    fun GetMovieDetailsById(id: Long, onSuccess: (text: String) -> Unit, onTimeout: () -> Unit){
        CallApi(ArrayList(), onSuccess, onTimeout, id)
    }

}