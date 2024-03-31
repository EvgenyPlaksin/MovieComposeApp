package com.lnight.moviecomposeapp.movie_list.data.repository

import com.lnight.moviecomposeapp.movie_list.data.data_sourse.remote.MoviesApi
import com.lnight.moviecomposeapp.movie_list.data.data_sourse.remote.dto.MovieListDto
import com.lnight.moviecomposeapp.movie_list.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: MoviesApi
): ApiRepository {

    override suspend fun getMovieList(page: Int, apiKey: String): MovieListDto {
        return api.discoverMovies(
            page = page,
            apiKey = apiKey,
            includeAdult = true,
            includeVideo = false,
            sortBy = "popularity.desc",
            language = "en-US"
        )
    }

}