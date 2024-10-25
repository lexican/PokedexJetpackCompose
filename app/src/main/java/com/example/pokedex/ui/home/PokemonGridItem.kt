package com.example.pokedex.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.pokedex.ui.theme.inactiveTabColor

@Composable
fun PokemonGridItem(pokemon: PokemonData) {
    Column {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .height(110.dp),
            model = pokemon.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "Pokemon Image"
        )
        Box(
            modifier = Modifier
                .background(
                    Color(0xFFF3F9EF), shape = RoundedCornerShape(
                        bottomStart = 4.dp,
                        bottomEnd = 4.dp,
                        topStart = 0.dp,
                        topEnd = 0.dp,
                    )
                )
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 10.dp)

        ) {
            Column {
                Text(
                    text = "#001",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = inactiveTabColor
                )
                Text(
                    text = "Bulbasaur",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                )
                Text(
                    text = "Grass, Poison", fontSize = 12.sp, color = inactiveTabColor
                )
            }

        }
    }
}
