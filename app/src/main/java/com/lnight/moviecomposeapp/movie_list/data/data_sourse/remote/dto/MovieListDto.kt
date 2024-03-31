package com.lnight.moviecomposeapp.movie_list.data.data_sourse.remote.dto

data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)