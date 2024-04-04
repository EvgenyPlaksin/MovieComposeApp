package com.lnight.moviecomposeapp.movie_details.data.data_source.remote

import com.lnight.moviecomposeapp.movie_details.data.data_source.remote.dto.MovieDetailsDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieDetailsApi {

    @Headers("Accept: application/json")
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Header("Authorization") apiKey: String
    ): MovieDetailsDto
}