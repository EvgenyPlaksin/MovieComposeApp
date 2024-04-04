package com.lnight.moviecomposeapp.movie_details.domain.repository

import com.lnight.moviecomposeapp.movie_details.data.data_source.remote.dto.MovieDetailsDto

interface ApiRepository {

    suspend fun getMovieDetails(movieId: Int, apiKey: String): MovieDetailsDto
}