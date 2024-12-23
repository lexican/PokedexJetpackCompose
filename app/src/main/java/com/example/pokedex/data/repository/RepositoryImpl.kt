package com.example.pokedex.data.repository

import com.example.pokedex.data.models.ApiResponse
import com.example.pokedex.data.service.ApiService
import com.example.pokedex.data.models.Pokemon
import com.example.pokedex.data.models.PokemonDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override fun getPokemonList(limit: Int, offset: Int): Flow<ApiResponse<List<PokemonDetails>>> =
        flow {
            try {
                val response = apiService.getPokemonList(limit, offset)
                val detailedPokemonList = mutableListOf<PokemonDetails>()
                response.results.forEach { pokemon: Pokemon ->
                    val pokemonDetails = apiService.getPokemonDetails(pokemon.url)
                    detailedPokemonList.add(pokemonDetails)
                }
                emit(ApiResponse.Success(detailedPokemonList))
            } catch (e: Exception) {
                emit(ApiResponse.Error("An error occurred: ${e.localizedMessage}"))
            }
        }

    override fun getSavedPokemons(savedPokemons: Set<Int>): Flow<ApiResponse<List<PokemonDetails>>> =
        flow {
            try {
                val detailedPokemonList = mutableListOf<PokemonDetails>()
                savedPokemons.forEach { pokemonId: Int ->
                    val pokemonDetails = apiService.getPokemonDetails("https://pokeapi.co/api/v2/pokemon/${pokemonId}")
                    detailedPokemonList.add(pokemonDetails)
                }
                emit(ApiResponse.Success(detailedPokemonList))
            } catch (e: Exception) {
                emit(ApiResponse.Error("An error occurred: ${e.localizedMessage}"))
            }
        }
}
