package com.example.pokedex.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pokedex.ui.theme.PokedexJetpackComposeTheme

@Composable
fun PokedexApp() {
    PokedexJetpackComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PokedexNavGraph()
        }
    }
}