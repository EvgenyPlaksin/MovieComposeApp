package com.lnight.moviecomposeapp.movie_details.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_details.domain.use_case.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel

class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailsState())
    val state: State<MovieDetailsState> = _state

    init {
        getMovieDetails()
    }

    fun getMovieDetails() {
        val movieId = savedStateHandle.get<Int>("movie_id")
        if (movieId == null || movieId == -1) {
            _state.value = state.value.copy(
                error = "Unknown error occurred"
            )
            return
        }
        getMovieDetailsUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message ?: "Unknown error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        movieDetails = result.data
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}