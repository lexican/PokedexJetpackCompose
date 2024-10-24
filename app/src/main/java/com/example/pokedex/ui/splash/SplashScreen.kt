package com.example.pokedex.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.R
import com.example.pokedex.ui.PokedexAppDestinations
import com.example.pokedex.ui.theme.PokedexJetpackComposeTheme
import com.example.pokedex.ui.theme.primaryColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.popBackStack()
        navController.navigate(PokedexAppDestinations.HOME_ROUTE)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
    ) {
        Row() {
            Image(
                painterResource(R.drawable.logo),
                contentDescription = "Pokedex Logo",
                contentScale = ContentScale.Fit
            )
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    "POKEMON",
                    fontSize = 16.sp,
                    color = Color.White,

                    )
                Text(
                    "Pokedex",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold, color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    val navController = rememberNavController()
    PokedexJetpackComposeTheme {
        SplashScreen(navController = navController)
    }
}