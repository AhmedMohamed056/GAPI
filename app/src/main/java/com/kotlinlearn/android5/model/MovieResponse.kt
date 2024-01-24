package com.kotlinlearn.android5.model

import java.io.Serializable


data class MovieResponse(
    val page : Int,
    val results : List<Movie>,
    val total_pages : Int,
    val total_results : Int
)

data class Movie(
    val id : Int,
    val backdrop_path : String,
    val overview : String,
    val poster_path : String,
    val release_date : String,
    val title : String,
    val vote_average : Double
):Serializable