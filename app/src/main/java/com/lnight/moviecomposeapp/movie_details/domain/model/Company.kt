package com.lnight.moviecomposeapp.movie_details.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val name: String,
    val logoPath: String
)
