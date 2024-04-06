package com.lnight.moviecomposeapp.movie_list.domain.use_case

import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList
import com.lnight.moviecomposeapp.movie_list.domain.repository.LocalRepository
import javax.inject.Inject

class SaveMovieUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(movieList: MovieList) {
        try {
            localRepository.insertMovieList(movieList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}