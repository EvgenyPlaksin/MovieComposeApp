package com.lnight.moviecomposeapp.movie_details.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_details.domain.use_case.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailsState())
    val state: State<MovieDetailsState> = _state

    init {
        val id = savedStateHandle.get<Int>("movie_id")
        getMovieDetails(id)
    }

    private fun getMovieDetails(movieId: Int?) {
        if(movieId == null || movieId == -1) {
            _state.value = state.value.copy(
                error = "Unknown error occurred"
            )
            return
        }
        viewModelScope.launch {
            when(val result = getMovieDetailsUseCase(movieId)) {
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
        }
    }
}