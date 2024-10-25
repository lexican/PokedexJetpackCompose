package com.example.pokedex.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.theme.PokedexJetpackComposeTheme
import com.example.pokedex.ui.theme.backgroundColor


@Composable
fun PokemonGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentPadding = PaddingValues(top = 20.dp, start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between columns
        verticalArrangement = Arrangement.spacedBy(12.dp), // Space between rows
    ) {
        items(pokemonList.size) { index: Int ->
            PokemonGridItem(pokemon = pokemonList[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonGridPreview() {
    PokedexJetpackComposeTheme {
        PokemonGrid()
    }
}