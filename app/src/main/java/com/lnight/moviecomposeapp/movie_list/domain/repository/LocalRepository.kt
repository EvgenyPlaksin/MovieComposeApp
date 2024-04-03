package com.lnight.moviecomposeapp.movie_list.domain.repository

import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList

interface LocalRepository {

    suspend fun getMovies(): MovieList

    suspend fun insertMovieList(movieList: MovieList)
}