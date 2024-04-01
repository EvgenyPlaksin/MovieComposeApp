package com.lnight.moviecomposeapp.movie_list.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnight.moviecomposeapp.movie_list.domain.DefaultPaginator
import com.lnight.moviecomposeapp.movie_list.domain.use_case.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state

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

    fun search(query: String) {
        _state.value = state.value.copy(
            isSearching = true
        )
        val movieList = _state.value.movieList
        if(!movieList?.results.isNullOrEmpty()) {
            _state.value = state.value.copy(
                movieList = movieList?.copy(
                    results = movieList.results.filter { it.title.contains(query, ignoreCase = true) }
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