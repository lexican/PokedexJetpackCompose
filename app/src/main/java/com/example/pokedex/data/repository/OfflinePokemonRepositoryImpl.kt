package com.example.pokedex.data.repository

import com.example.pokedex.data.dao.PokemonDao
import com.example.pokedex.data.models.PokemonFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OfflinePokemonRepositoryImpl(private val pokemonFavoriteDao: PokemonDao) :
    OfflinePokemonRepository {
    override suspend fun addFavorite(pokemonId: Int) {
        pokemonFavoriteDao.insertPokemon(PokemonFavorite(pokemonId))
    }

    override suspend fun removeFavorite(pokemonId: Int) {
        pokemonFavoriteDao.removePokemon(pokemonId)
    }

    override fun getAllPokemons(): Flow<List<PokemonFavorite>> = flow {
        emit(pokemonFavoriteDao.getAllPokemons())
    }
}

