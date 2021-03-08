package com.example.topratedmovies.models

data class Details(
    var name: String,
    var original_language: String,
    var original_title: String,
    var overview: String?,
    var release_date: String,
    var title: String,
    var revenue: Long,
    var popularity: Number
)
