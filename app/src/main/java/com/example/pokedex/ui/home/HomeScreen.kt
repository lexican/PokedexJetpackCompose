package com.example.pokedex.ui.home

import HomeTabRow
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.R
import com.example.pokedex.ui.theme.PokedexJetpackComposeTheme
import com.example.pokedex.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(name: String, modifier: Modifier = Modifier) {
    var state by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            Box {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = textColor,
                    ),
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painterResource(R.drawable.logo),
                                contentDescription = "Pokedex Logo",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.width(24.dp),
                            )
                            Spacer(modifier = Modifier.width(width = 10.dp))
                            Text(
                                "Pokedex",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    },
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            color = Color.Black.copy(alpha = 0.1f) // Adjust opacity for subtle shadow effect
                        )
                )
            }
        },
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column {
                HomeTabRow(state = state, onClick = {
                    state = it
                })
                
                when (state) {
                    0 -> PokemonGrid()
                    1 -> Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Favourites",
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    PokedexJetpackComposeTheme {
        HomeScreen("Android")
    }
}