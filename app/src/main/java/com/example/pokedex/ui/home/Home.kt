package com.example.pokedex.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokedex.ui.theme.PokedexJetpackComposeTheme

@Composable
fun HomeScreen(name: String, modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Text(name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexJetpackComposeTheme {
        HomeScreen("Android")
    }
}