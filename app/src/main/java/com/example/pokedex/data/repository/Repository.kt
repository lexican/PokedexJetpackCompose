package com.example.pokedex.data.repository

import com.example.pokedex.data.models.ApiResponse
import com.example.pokedex.data.models.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPokemonList(limit: Int, offset: Int): Flow<ApiResponse<List<PokemonDetails>>>
}
