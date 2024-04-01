package com.lnight.moviecomposeapp.movie_list.presentation

import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList

data class MovieListState(
    val isLoading: Boolean = false,
    val movieList: MovieList? = null,
    val error: String = "",
    val page: Int = 1,
    val searchText: String = "",
    val endReached: Boolean = false,
    val isSearching: Boolean = false
)