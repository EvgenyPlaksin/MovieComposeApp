package com.lnight.moviecomposeapp.movie_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.lnight.moviecomposeapp.movie_list.domain.model.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable(onClick = onMovieClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val request = ImageRequest.Builder(LocalContext.current)
            .data(movie.posterPath)
            .crossfade(true)
            .build()

        SubcomposeAsyncImage(
            model = request,
            contentDescription = "Movie poster",
            error = {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "Error",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            },
            loading = {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .scale(0.5f)
                )
            },
            modifier = Modifier
                .size(height = 135.dp, width = 90.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "• Title: ${movie.title}",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 16.sp
            )
            Text(
                text = "• Adult: ${movie.isAdult}",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 16.sp
            )

            val date = LocalDate.parse(movie.releaseDate)
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            Text(
                text = "• Release date: $date",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview
@Composable
private fun MovieListItemPreview() {
    MovieListItem(
        movie = Movie(
            id = 823464,
            isAdult = false,
            title = "Godzilla x Kong: The New Empire",
            releaseDate = "2024-03-27",
            posterPath = "https://image.tmdb.org/t/p/original/gmGK5Gw5CIGMPhOmTO0bNA9Q66c.jpg"
        )
    ) {

    }
}