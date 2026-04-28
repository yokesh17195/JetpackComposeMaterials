package com.emi.udemyapp.ui.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object MovieDetails : Routes("details")
}