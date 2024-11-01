package com.example.pokedex.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokedex.data.models.PokemonDetails
import com.example.pokedex.utils.calculateBMI

@Composable
fun BodyInfo(pokemon: PokemonDetails) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White)
    ) {
        Row() {
            InfoItem(title = "Height", value = pokemon.height.toString())
            InfoItem(title = "Weight", value = pokemon.weight.toString())
            InfoItem(
                title = "BMI",
                value = calculateBMI(
                    height = pokemon.height?.toDouble() ?: 0.0,
                    weight = pokemon.weight?.toDouble() ?: 0.0,
                ),
            )
        }
    }
}
