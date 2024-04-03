package com.lnight.moviecomposeapp.movie_list.domain.use_case

import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList
import com.lnight.moviecomposeapp.movie_list.domain.repository.LocalRepository
import javax.inject.Inject

class GetSavedMoviesUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(): Resource<MovieList> {
        return try {
            val data = localRepository.getMovies()
            Resource.Success(data)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}