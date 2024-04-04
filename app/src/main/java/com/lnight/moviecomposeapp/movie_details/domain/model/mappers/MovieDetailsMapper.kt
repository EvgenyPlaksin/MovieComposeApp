package com.lnight.moviecomposeapp.movie_details.domain.model.mappers

import com.lnight.moviecomposeapp.common.Constants.BASE_IMAGE_URL
import com.lnight.moviecomposeapp.movie_details.data.data_source.remote.dto.MovieDetailsDto
import com.lnight.moviecomposeapp.movie_details.data.data_source.remote.dto.ProductionCompany
import com.lnight.moviecomposeapp.movie_details.domain.model.Company
import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        isAdult = adult,
        title = title,
        posterPath = BASE_IMAGE_URL + poster_path,
        genres = genres.map { it.name },
        homepage = homepage,
        overview = overview,
        productionCompanies = production_companies.map { it.toCompany() },
        status = status
    )
}

fun ProductionCompany.toCompany(): Company {
    return Company(
        name = name,
        logoPath = BASE_IMAGE_URL + logo_path
    )
}