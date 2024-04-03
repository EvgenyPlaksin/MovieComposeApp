package com.lnight.moviecomposeapp.movie_list.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnight.moviecomposeapp.common.Resource
import com.lnight.moviecomposeapp.movie_list.domain.DefaultPaginator
import com.lnight.moviecomposeapp.movie_list.domain.use_case.SaveMovieUseCase
import com.lnight.moviecomposeapp.movie_list.domain.use_case.GetSavedMoviesUseCase
import com.lnight.moviecomposeapp.movie_list.domain.use_case.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state

    init {
        getSavedMovies()
    }

    private val paginator = DefaultPaginator(
        initialKey = state.value.page,
        onLoadUpdated = {
            _state.value = state.value.copy(
                isLoading = it
            )
        },
        onRequest = { nextPage ->
            movieListUseCase(nextPage)
        },
        getNextKey = {
            state.value.page + 1
        },
        onError = {
            _state.value = state.value.copy(
                error = it.message ?: "Unknown error occurred"
            )
            if(state.value.movieList == null) {
                getSavedMovies()
            }
        },
        onSuccess = { items, newKey ->
            if(items.data != null) {
                if(state.value.movieList != null) {
                    val newList = state.value.movieList!!.copy(
                        results = state.value.movieList!!.results + items.data.results,
                        totalPages = items.data.totalPages,
                    )
                    _state.value = state.value.copy(
                        movieList = newList,
                        page = newKey,
                        endReached = items.data.results.isEmpty()
                    )
                } else {
                    _state.value = state.value.copy(
                        movieList = items.data,
                        page = newKey,
                        endReached = items.data.results.isEmpty()
                    )
                }
                state.value.movieList?.let {
                    saveMovieUseCase(it)
                }
                }
        }
    )

    init {
        getMovieList()
    }

     fun getMovieList() {
        if(state.value.isSearching) return
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private fun getSavedMovies() {
        if(state.value.isSearching && state.value.movieList != null) return
        viewModelScope.launch {
            when(val result = getSavedMoviesUseCase()) {
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
                    if(result.data != null) {
                        _state.value = state.value.copy(
                            isLoading = false,
                            page = result.data.page + 1,
                            movieList = result.data
                        )
                    }
                }
            }
        }
    }

    fun search(query: String) {
        _state.value = state.value.copy(
            isSearching = true
        )
        val movieList = _state.value.movieList
        if(!movieList?.results.isNullOrEmpty()) {
            _state.value = state.value.copy(
                movieList = movieList?.copy(
                    results = movieList.results.filter { it.title.contains(query, ignoreCase = true) }.toSet()
                )
            )
        }
    }

    fun onInputText(text: String) {
        if(text.isBlank()) {
            _state.value = state.value.copy(
                isSearching = false
            )
            paginator.reset()
            _state.value = state.value.copy(
                movieList = null
            )
            getMovieList()
        }
        _state.value = state.value.copy(
            searchText = text
        )
    }
}