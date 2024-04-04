package com.lnight.moviecomposeapp.movie_details.domain.use_case

import com.lnight.moviecomposeapp.common.Constants.API_KEY
import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails
import com.lnight.moviecomposeapp.movie_details.domain.model.mappers.toMovieDetails
import com.lnight.moviecomposeapp.movie_details.domain.repository.ApiRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetMovieDetailsUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(movieId: Int): Resource<MovieDetails> {
        return try {
            val data = apiRepository.getMovieDetails(movieId, API_KEY)
            Resource.Success(data.toMovieDetails())
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server, check your internet connection")
        }
    }

}