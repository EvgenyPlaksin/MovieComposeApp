package com.lnight.moviecomposeapp.movie_details.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class MovieDetails(
    @PrimaryKey
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
