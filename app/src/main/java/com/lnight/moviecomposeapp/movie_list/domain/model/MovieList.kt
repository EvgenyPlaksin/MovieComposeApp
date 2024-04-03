package com.lnight.moviecomposeapp.movie_list.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieList(
    @PrimaryKey
    val page: Int,
    val results: Set<Movie>,
    val totalPages: Int,
)