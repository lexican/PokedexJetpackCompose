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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.pokedex.data.models.PokemonDetails
import com.example.pokedex.ui.theme.inactiveTabColor
import com.example.pokedex.utils.convertPokemonTypesToString
import com.example.pokedex.utils.convertToIdHash
import com.example.pokedex.utils.getPokemonImageBackgroundColor
import java.util.Locale

@Composable
fun PokemonGridItem(pokemon: PokemonDetails) {
    Column {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .height(110.dp)
                .background(
                    color = getPokemonImageBackgroundColor(
                        pokemon.name
                            .toString()
                            .first()
                            .toString()
                    )
                ),
            model = pokemon.sprites?.other?.officialArtwork?.frontDefault,
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
                    text = convertToIdHash(pokemon.id ?: 1),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = inactiveTabColor
                )
                Text(
                    text = pokemon.name.toString()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                )
                Text(
                    text = convertPokemonTypesToString(pokemon.types ?: emptyList()),
                    fontSize = 12.sp,
                    color = inactiveTabColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}
