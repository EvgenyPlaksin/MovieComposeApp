package com.lnight.moviecomposeapp.movie_list.domain.model

data class Movie(
    val id: Int,
    val isAdult: Boolean,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val overview: String,
)