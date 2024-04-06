package com.lnight.moviecomposeapp.movie_details.domain.use_case

import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails
import com.lnight.moviecomposeapp.movie_details.domain.repository.LocalRepository
import javax.inject.Inject

class SaveMovieDetailsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(movieDetails: MovieDetails) {
        try {
            localRepository.insertMovieDetails(movieDetails)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}