package com.lnight.moviecomposeapp.movie_list.data.data_sourse.remote

import com.lnight.moviecomposeapp.movie_list.data.data_sourse.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApi {

    @Headers("Accept: application/json")
    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String,
        @Header("Authorization") apiKey: String
    ): MovieListDto

}