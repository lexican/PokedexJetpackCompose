package com.example.pokedex.data.repository

import com.example.pokedex.data.models.PokemonFavorite
import kotlinx.coroutines.flow.Flow

interface OfflinePokemonRepository {
    suspend fun addFavorite(pokemonId: Int)
    suspend fun removeFavorite(pokemonId: Int)
    fun getAllPokemons(): Flow<List<PokemonFavorite>>
}