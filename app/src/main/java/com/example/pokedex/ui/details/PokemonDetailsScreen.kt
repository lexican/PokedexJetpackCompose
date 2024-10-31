package com.example.pokedex.ui.details

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.home.HomeViewModel
import com.example.pokedex.ui.theme.primaryColor

@Composable
fun PokemonDetailsScreen(viewModel: HomeViewModel) {
    val pokemon = viewModel.selectedPokemon.collectAsState()
    var isFavorite by remember { mutableStateOf(pokemon.value?.let { viewModel.isFavorite(it) }) }

    Scaffold(floatingActionButton = {
        if (isFavorite == true) {

            Button(
                onClick = {
                    if (pokemon.value != null) {
                        viewModel.toggleFavouritePokemon(pokemon.value)
                        isFavorite = false
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5DEFF))
            ) {
                Text(
                    "Remove from favourites",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    color = primaryColor,
                )
            }
        } else {
            Button(
                onClick = {
                    Log.i("PokemonDetailsScreen", "pokemon details ${pokemon.value}")
                    if (pokemon.value != null) {
                        viewModel.toggleFavouritePokemon(pokemon = pokemon.value)
                        isFavorite = true
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
            ) {
                Text(
                    "Mark as favourite",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.White,
                )
            }
        }


    }) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
        }
    }
}