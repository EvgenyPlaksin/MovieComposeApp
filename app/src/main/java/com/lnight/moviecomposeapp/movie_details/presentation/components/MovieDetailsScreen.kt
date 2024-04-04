package com.lnight.moviecomposeapp.movie_details.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lnight.moviecomposeapp.movie_details.presentation.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    isCompactScreen: Boolean
) {
    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val state by viewModel.state

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = state.movieDetails.toString()
        )
    }
}