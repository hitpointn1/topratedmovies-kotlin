package com.example.topratedmovies.models

data class MovieDetailsById (
        var title: String,
        var original_language: String,
        var original_title: String,
        var popularity: Number,
        var release_date: String,
        var budget: Int
)