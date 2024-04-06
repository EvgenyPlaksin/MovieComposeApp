package com.lnight.moviecomposeapp.movie_details.domain.repository

import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails

interface LocalRepository {

    suspend fun getMovieDetailsById(id: Int): MovieDetails

    suspend fun insertMovieDetails(movieDetails: MovieDetails)
}