package com.lnight.moviecomposeapp.movie_details.presentation

import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetails? = null,
    val error: String = "",
)
