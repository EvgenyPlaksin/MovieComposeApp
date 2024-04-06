package com.lnight.moviecomposeapp.movie_details.domain.use_case

import com.lnight.moviecomposeapp.common.Constants.API_KEY
import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails
import com.lnight.moviecomposeapp.movie_details.domain.model.mappers.toMovieDetails
import com.lnight.moviecomposeapp.movie_details.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetMovieDetailsUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    operator fun invoke(movieId: Int): Flow<Resource<MovieDetails>> = flow {
        try {
            emit(Resource.Loading())
            val data = apiRepository.getMovieDetails(movieId, API_KEY)
            emit(Resource.Success(data.toMovieDetails()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server, check your internet connection"))
        }
    }

}