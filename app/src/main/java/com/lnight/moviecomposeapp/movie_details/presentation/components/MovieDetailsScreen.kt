package com.lnight.moviecomposeapp.movie_details.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lnight.moviecomposeapp.common.RetrySection
import com.lnight.moviecomposeapp.movie_details.presentation.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    isCompactScreen: Boolean
) {
    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val state by viewModel.state

    if (state.isLoading && state.movieDetails == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.error.isNotBlank() && state.movieDetails == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            RetrySection(
                text = state.error,
                onClick = {
                    viewModel.getMovieDetails()
                }
            )
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MoviePoster(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                posterPath = state.movieDetails?.posterPath ?: "",
                backgroundImagePath = state.movieDetails?.backgroundImagePath ?: "",
                name = state.movieDetails?.title ?: ""
            )
        }
    }
}