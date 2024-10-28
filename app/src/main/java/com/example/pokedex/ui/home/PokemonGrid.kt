package com.example.pokedex.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedex.data.models.PokemonDetails
import com.example.pokedex.ui.theme.PokedexJetpackComposeTheme
import com.example.pokedex.ui.theme.backgroundColor


@Composable
fun PokemonGrid(viewModel: HomeViewModel = hiltViewModel(), lazyGridState: LazyGridState) {
    val pokemonState by viewModel.pokemonListState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        PokemonLazyVerticalGrid(isLoadingMore, pokemonState, lazyGridState)
    }
}

@Composable
fun PokemonLazyVerticalGrid(
    isLoadingMore: Boolean,
    pokemonList: List<PokemonDetails>,
    lazyGridState: LazyGridState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LazyVerticalGrid(
        state = lazyGridState,
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentPadding = PaddingValues(top = 20.dp, start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(pokemonList.size) { index: Int ->
            PokemonGridItem(pokemon = pokemonList[index])
        }
        if (isLoadingMore) {
            item(span = { GridItemSpan(3) }) { // Span the indicator across two columns
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
    LaunchedEffect(lazyGridState) {
        snapshotFlow { lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }.collect { lastVisibleIndex: Int? ->
            if (lastVisibleIndex != null && lastVisibleIndex >= pokemonList.size - 4) {
                viewModel.loadMorePokemon()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonGridPreview() {
    val pokemonGridState = rememberLazyGridState() // Retain state for PokemonGrid tab
    PokedexJetpackComposeTheme {
        PokemonGrid(lazyGridState = pokemonGridState)
    }
}