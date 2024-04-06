package com.lnight.moviecomposeapp.movie_details.domain.model

import java.time.LocalDate

data class MovieDetails(
    val id: Int,
    val isAdult: Boolean,
    val title: String,
    val posterPath: String,
    val backgroundImagePath: String,
    val genres: List<String>,
    val homepage: String,
    val overview: String,
    val productionCompanies: List<Company>,
    val duration: Int,
    val releaseDate: LocalDate
)
