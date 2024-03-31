package com.lnight.moviecomposeapp.movie_list.domain.use_case

import com.lnight.moviecomposeapp.common.Constants.API_KEY
import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList
import com.lnight.moviecomposeapp.movie_list.domain.model.mappers.toMovieList
import com.lnight.moviecomposeapp.movie_list.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieListUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    operator fun invoke(page: Int): Flow<Resource<MovieList>> = flow {
        try {
            emit(Resource.Loading())
            val data = apiRepository.getMovieList(page, API_KEY)
            emit(Resource.Success(data.toMovieList()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, check your internet connection"))
        }
    }

}