package com.lnight.moviecomposeapp.movie_list.data.repository

import com.lnight.moviecomposeapp.movie_list.data.data_source.local.MovieComposeDao
import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList
import com.lnight.moviecomposeapp.movie_list.domain.repository.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: MovieComposeDao
): LocalRepository {
    override suspend fun getMovies(): MovieList {
        return dao.getAllMovies()
    }

    override suspend fun insertMovieList(movieList: MovieList) {
        withContext(Dispatchers.IO) {
            dao.insertMovie(movieList)
        }
    }
}