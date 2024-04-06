package com.lnight.moviecomposeapp.movie_details.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails
import java.time.format.DateTimeFormatter

@Composable
fun InfoSection(
    movieDetails: MovieDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            text = movieDetails.title,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row {
                Canvas(modifier = Modifier.size(21.dp)) {
                    val color = when(movieDetails.isAdult) {
                        true -> Color.Red
                        false -> Color.Green
                    }

                    drawCircle(color = color, radius = 21f)
                }
                Spacer(modifier = Modifier.width(3.dp))

                val text = when(movieDetails.isAdult) {
                    true -> "Adult"
                    false -> "Not Adult"
                }
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = text,
                    fontSize = 14.sp
                )
            }
            Row {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "Duration",
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "${movieDetails.duration} min",
                    fontSize = 14.sp
                )
            }
            Row {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Release Date",
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = movieDetails.releaseDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    fontSize = 14.sp
                )
            }
        }
    }
}