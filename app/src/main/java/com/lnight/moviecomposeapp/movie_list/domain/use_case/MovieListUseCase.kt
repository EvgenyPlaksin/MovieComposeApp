package com.lnight.moviecomposeapp.movie_list.domain.use_case

import com.lnight.moviecomposeapp.common.Constants.API_KEY
import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList
import com.lnight.moviecomposeapp.movie_list.domain.model.mappers.toMovieList
import com.lnight.moviecomposeapp.movie_list.domain.repository.ApiRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieListUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(page: Int): Resource<MovieList> {
        return try {
            val data = apiRepository.getMovieList(page, API_KEY)
            Resource.Success(data.toMovieList())
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server, check your internet connection")
        }
    }

}