package com.lnight.moviecomposeapp.movie_details.data.repository

import com.lnight.moviecomposeapp.movie_details.data.data_source.remote.MovieDetailsApi
import com.lnight.moviecomposeapp.movie_details.data.data_source.remote.dto.MovieDetailsDto
import com.lnight.moviecomposeapp.movie_details.domain.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: MovieDetailsApi
): ApiRepository {

    override suspend fun getMovieDetails(movieId: Int, apiKey: String): MovieDetailsDto {
        return withContext(Dispatchers.IO) {
            api.getMovieDetails(
                movieId = movieId,
                apiKey = apiKey
            )
        }
    }
}