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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedex.data.models.PokemonDetails
import com.example.pokedex.ui.theme.backgroundColor


@Composable
fun PokemonGrid(
    viewModel: HomeViewModel,
    lazyGridState: LazyGridState,
    onPokemonItemClicked: () -> Unit,
) {
    val pokemonState by viewModel.pokemonListState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        if (isError && pokemonState.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                RetryButtonWithErrorMessage(onClick = {
                    viewModel.retry()
                })
            }
        } else {
            PokemonLazyVerticalGrid(
                isLoadingMore,
                pokemonState,
                lazyGridState,
                onPokemonItemClicked = onPokemonItemClicked,
                viewModel = viewModel,
            )
        }

    }
}

@Composable
fun PokemonLazyVerticalGrid(
    isLoadingMore: Boolean,
    pokemonList: List<PokemonDetails>,
    lazyGridState: LazyGridState,
    viewModel: HomeViewModel,
    onPokemonItemClicked: () -> Unit,
) {
    val isError by viewModel.isError.collectAsState()

    val poke by viewModel.selectedPokemon.collectAsState()


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
            PokemonGridItem(
                pokemon = pokemonList[index],
                onPokemonItemClicked = onPokemonItemClicked,
                viewModel = viewModel,
            )
        }
        if (isLoadingMore) {
            item(span = { GridItemSpan(3) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        } else if (isError && pokemonList.isNotEmpty()) {
            item(span = { GridItemSpan(3) }) {
                RetryButtonWithErrorMessage(onClick = {
                    viewModel.retry()
                })
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

//@Preview(showBackground = true)
//@Composable
//fun PokemonGridPreview(viewModel: HomeViewModel = hiltViewModel()) {
//    val pokemonGridState = rememberLazyGridState()
//    PokedexJetpackComposeTheme {
//        PokemonGrid(
//            lazyGridState = pokemonGridState, onPokemonItemClicked = {}, viewModel = viewModel,
//        )
//    }
//}