package com.lnight.moviecomposeapp.movie_list.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList
import com.lnight.moviecomposeapp.movie_list.domain.use_case.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state

    init {
        getMovieList(state.value.previousPage + 1)
    }

    private fun getMovieList(page: Int = 1) {
        movieListUseCase(page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data != null) {
                        _state.value = state.value.copy(
                            previousPage = page
                        )
                            _state.value = state.value.copy(
                                movieList = result.data,
                                isLoading = false
                            )
                    }
                }
                is Resource.Loading -> {
                    if(_state.value.movieList == null) {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message ?: "Unknown error occurred"
                    )
                }

            }
        }.launchIn(viewModelScope)
    }
}