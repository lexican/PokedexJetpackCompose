package com.example.pokedex.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.pokedex.data.models.PokemonDetails
import com.example.pokedex.utils.calculateAvgPower

@Composable
fun StatList(pokemon: PokemonDetails) {
    val stats = pokemon.stats ?: emptyList()

    val statItems = stats.map { poke ->
        BaseStatItem(
            title = poke.statDetail.name,
            statValue = poke.baseStat
        )
    } + BaseStatItem(
        title = "Avg. Power",
        statValue = calculateAvgPower(stats).toInt()
    )

    Column {
        statItems.forEach { statItem ->
            statItem
        }
    }

}