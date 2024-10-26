package com.example.pokedex.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.theme.PokedexJetpackComposeTheme
import com.example.pokedex.ui.theme.backgroundColor
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedex.data.models.ApiResponse


@Composable
fun PokemonGrid(viewModel: HomeViewModel = hiltViewModel()) {
    val pokemonState by viewModel.pokemonListState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getPokemonList(limit = 20, offset = 0)
    }
    when (val state = pokemonState) {
        is ApiResponse.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        is ApiResponse.Success -> {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor),
                contentPadding = PaddingValues(top = 20.dp, start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between columns
                verticalArrangement = Arrangement.spacedBy(12.dp), // Space between rows
            ) {
                items(state.data.size) { index: Int ->
                    PokemonGridItem(pokemon = state.data[index])
                }
            }
        }

        is ApiResponse.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Error: ${state.message}", color = Color.Red)
            }
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