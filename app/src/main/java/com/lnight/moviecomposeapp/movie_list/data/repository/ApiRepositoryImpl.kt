package com.lnight.moviecomposeapp.movie_list.data.repository

import com.lnight.moviecomposeapp.movie_list.data.data_source.remote.MovieListApi
import com.lnight.moviecomposeapp.movie_list.data.data_source.remote.dto.MovieListDto
import com.lnight.moviecomposeapp.movie_list.domain.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: MovieListApi
): ApiRepository {

    override suspend fun getMovieList(page: Int, apiKey: String): MovieListDto {
        return withContext(Dispatchers.IO) {
            api.discoverMovies(
                page = page,
                apiKey = apiKey,
                includeAdult = true,
                includeVideo = false,
                sortBy = "popularity.desc",
                language = "en-US"
            )
        }
    }
}