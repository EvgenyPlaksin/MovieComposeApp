package com.lnight.moviecomposeapp.movie_list.domain.repository

import com.lnight.moviecomposeapp.movie_list.data.data_source.remote.dto.MovieListDto

interface ApiRepository {

    suspend fun getMovieList(page: Int, apiKey: String): MovieListDto

}