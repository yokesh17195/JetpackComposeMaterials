package com.emi.udemyapp.ui.movieapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emi.udemyapp.ui.navigation.Routes

@Composable
fun MovieApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(modifier = Modifier.padding(innerPadding), navController = navController, startDestination = Routes.Home.route) {
        // Home
        composable(Routes.Home.route) {
            // pass the navController
            MoviesListScreen(navController = navController)
        }
        // Settings
        // "/{id}" - its the argument passed down from homeScreen
        composable(Routes.MovieDetails.route + "/{movie}") { navBackStack ->
            // Extracting the argument
            val movie = navBackStack.arguments?.getString("movie")?:""
            MovieDetailsScreen(navController = navController,movie)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieAppPreview(){
    MovieApp(PaddingValues())
}