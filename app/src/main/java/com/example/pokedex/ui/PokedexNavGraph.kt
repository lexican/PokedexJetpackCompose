package com.example.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.pokedex.ui.details.PokemonDetailsScreen
import com.example.pokedex.ui.home.HomeScreen
import com.example.pokedex.ui.home.HomeViewModel
import com.example.pokedex.ui.splash.SplashScreen

object PokedexAppDestinations {
    const val SPLASH_ROUTE = "splash"
    const val HOME_ROUTE = "home"
    const val POKEMON_DETAILS = "details"
}


@Composable
fun PokedexNavGraph() {
    val navController = rememberNavController()
    val viewModel: HomeViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = PokedexAppDestinations.SPLASH_ROUTE,

        ) {
        composable(route = PokedexAppDestinations.SPLASH_ROUTE) {
            SplashScreen(navController = navController)
        }
        navigation(
            startDestination = PokedexAppDestinations.HOME_ROUTE, route = "homeNavGraph"
        ) {
//            composable(route = PokedexAppDestinations.HOME_ROUTE) {backStackEntry ->
//                val viewModel: HomeViewModel = hiltViewModel(backStackEntry)
//                HomeScreen(navController = navController, viewModel = viewModel)
//            }
//            composable(route = PokedexAppDestinations.POKEMON_DETAILS) {backStackEntry ->
//                val viewModel: HomeViewModel =
//                    if (navController.previousBackStackEntry != null) hiltViewModel(
//                        navController.previousBackStackEntry!!
//                    ) else hiltViewModel()
//                PokemonDetailsScreen(viewModel = viewModel)
//            }
            composable(route = PokedexAppDestinations.HOME_ROUTE) { backStackEntry ->
                //val viewModel: HomeViewModel = hiltViewModel(backStackEntry)
                HomeScreen(navController = navController, viewModel = viewModel)
            }
            composable(route = PokedexAppDestinations.POKEMON_DETAILS) { backStackEntry ->
                // Retrieve the ViewModel instance tied to the navigation graph
                //val viewModel: HomeViewModel = hiltViewModel(backStackEntry)
                PokemonDetailsScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}