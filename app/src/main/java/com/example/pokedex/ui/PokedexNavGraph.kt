package com.example.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.ui.home.HomeScreen
import com.example.pokedex.ui.splash.SplashScreen

object PokedexAppDestinations {
    const val SPLASH_ROUTE = "splash"
    const val HOME_ROUTE = "home"
}


@Composable
fun PokedexNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PokedexAppDestinations.SPLASH_ROUTE,

        ) {
        composable(route = PokedexAppDestinations.SPLASH_ROUTE) {
            SplashScreen(navController = navController)
        }
        composable(route = PokedexAppDestinations.HOME_ROUTE) {
            HomeScreen()
        }
    }
}