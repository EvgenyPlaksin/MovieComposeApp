package com.lnight.moviecomposeapp.movie_details.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun MoviePoster(
    posterPath: String,
    backgroundImagePath: String,
    name: String,
    modifier: Modifier = Modifier
) {

    val bottomFade = Brush.verticalGradient(
        0.7f to MaterialTheme.colorScheme.background,
        1f to Color.Transparent
    )

    val posterImageRequest = ImageRequest.Builder(LocalContext.current)
        .data(posterPath)
        .crossfade(true)
        .build()

    val backgroundImageRequest = ImageRequest.Builder(LocalContext.current)
        .data(backgroundImagePath)
        .crossfade(true)
        .build()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = backgroundImageRequest,
            contentScale = ContentScale.FillBounds,
            contentDescription = "Background image",
            alpha = 0.5f,
            colorFilter = ColorFilter.tint(
                color = Color.Black,
                BlendMode.Lighten
            ),
            modifier = Modifier
                .fillMaxSize()
                .fadingEdge(bottomFade)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .clip(RoundedCornerShape(24.dp))
        ) {
            SubcomposeAsyncImage(
                model = posterImageRequest,
                contentDescription = name,
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .scale(0.5f)
                    )
                }
            )
        }
    }

}

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }