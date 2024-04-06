package com.lnight.moviecomposeapp.movie_details.data.repository

import com.lnight.moviecomposeapp.movie_details.data.data_source.local.MovieDetailsDao
import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails
import com.lnight.moviecomposeapp.movie_details.domain.repository.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
   private val dao: MovieDetailsDao
): LocalRepository {

    override suspend fun getMovieDetailsById(id: Int): MovieDetails {
        return withContext(Dispatchers.IO) {
            dao.getMovieDetailsById(id)
        }
    }


    override suspend fun insertMovieDetails(movieDetails: MovieDetails) {
        withContext(Dispatchers.IO) {
            dao.insertMovieDetails(movieDetails)
        }
    }
}