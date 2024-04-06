package com.lnight.moviecomposeapp.movie_details.domain.use_case

import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails
import com.lnight.moviecomposeapp.movie_details.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSavedMovieDetailsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    operator fun invoke(movieId: Int): Flow<Resource<MovieDetails>> = flow {
        try {
            emit(Resource.Loading())
            val data = localRepository.getMovieDetailsById(movieId)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}