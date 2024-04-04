package com.lnight.moviecomposeapp.movie_list.domain.model.mappers

import com.lnight.moviecomposeapp.common.Constants.BASE_IMAGE_URL
import com.lnight.moviecomposeapp.movie_list.data.data_source.remote.dto.MovieDto
import com.lnight.moviecomposeapp.movie_list.data.data_source.remote.dto.MovieListDto
import com.lnight.moviecomposeapp.movie_list.domain.model.Movie
import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList

fun MovieListDto.toMovieList(): MovieList {
    return MovieList(
        page = page,
        results = results.map { it.toMovie() }.toSet(),
        totalPages = total_pages
    )
}

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        isAdult = adult,
        title = title,
        releaseDate = release_date,
        posterPath = BASE_IMAGE_URL + poster_path
    )
}