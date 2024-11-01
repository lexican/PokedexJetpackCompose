package com.example.pokedex.ui.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pokedex.ui.home.HomeViewModel
import com.example.pokedex.ui.theme.backgroundColor
import com.example.pokedex.ui.theme.primaryColor
import com.example.pokedex.ui.theme.textColor
import com.example.pokedex.utils.getPokemonImageBackgroundColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsScreen(navController: NavHostController, viewModel: HomeViewModel) {
    val pokemon = viewModel.selectedPokemon.collectAsState()
    var isFavorite by remember { mutableStateOf(pokemon.value?.let { viewModel.isFavorite(it) }) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = getPokemonImageBackgroundColor(
                        pokemon.value?.name.toString().first().toString()
                    ),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }


                },
            )
        },
        floatingActionButton = {
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


        },
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                pokemon.value?.let { TitleSection(pokemon = it) }
                //
                pokemon.value?.let { BodyInfo(pokemon = it) }

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(backgroundColor)
                )
                Box(modifier = Modifier.padding(15.dp)) {
                    Text(
                        text = "Base Stats",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = textColor
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(backgroundColor)
                )
                //
                //
                //StatCard(title = "Hp", statValue = 20)
                pokemon.value?.let { StatList(pokemon = it) }
            }
        }
    }
}




