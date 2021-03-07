package com.example.topratedmovies.models

data class TopRatedMoviesResponse(
    var page: Int,
    var results: Array<Movie>,
    var total_results: Int,
    var total_pages: Int
)