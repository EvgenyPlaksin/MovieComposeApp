package com.lnight.moviecomposeapp.movie_list.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val isAdult: Boolean,
    val title: String,
    val releaseDate: String,
    val posterPath: String
)
