package com.lnight.moviecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lnight.moviecomposeapp.common.Screen
import com.lnight.moviecomposeapp.movie_details.presentation.components.MovieDetailsScreen
import com.lnight.moviecomposeapp.movie_list.presentation.MovieListViewModel
import com.lnight.moviecomposeapp.movie_list.presentation.components.MovieListScreen
import com.lnight.moviecomposeapp.ui.theme.MovieComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )
        setContent {
            MovieComposeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val systemUiController = rememberSystemUiController()
                    val darkColors = !isSystemInDarkTheme()

                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            Color.Transparent,
                            darkColors
                        )
                    }
                    val navController = rememberNavController()

                    val movieListViewModel: MovieListViewModel by viewModels()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.ListScreen.route
                    ) {
                        composable(Screen.ListScreen.route) {
                            MovieListScreen(
                                navController = navController,
                                viewModel = movieListViewModel
                            )
                        }
                        composable(
                            route = Screen.DetailScreen.route + "/{movie_id}",
                            arguments = listOf(
                                navArgument("movie_id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            MovieDetailsScreen()
                        }
                    }

                }
            }
        }
    }
}