package com.lnight.moviecomposeapp.movie_list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lnight.moviecomposeapp.common.Screen
import com.lnight.moviecomposeapp.movie_list.presentation.MovieListViewModel

@Composable
fun MovieListScreen(
    navController: NavController
) {
    val viewModel: MovieListViewModel = hiltViewModel()
    val state by viewModel.state

    if(state.isLoading && state.movieList?.results.isNullOrEmpty()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
            CircularProgressIndicator()
        }
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchBar(
                text = state.searchText,
                placeHolder = "Search...",
                onSearch = {
                    viewModel.search(state.searchText)
                },
                onInputText = viewModel::onInputText
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.movieList?.let { movieList ->
                    items(movieList.results.toList()) { movie ->
                        if(movie.id == movieList.results.last().id && !state.endReached && !state.isLoading) {
                            viewModel.getMovieList()
                        }
                        MovieListItem(movie = movie) {
                            navController.navigate(Screen.DetailScreen.route + "/${movie.id}")
                        }
                    }
                }
                if(state.isLoading && !state.movieList?.results.isNullOrEmpty()) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
}
