package com.lnight.moviecomposeapp.movie_list.domain.model

data class MovieList(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
)