package com.example.pokedex.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.models.PokemonFavorite

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(favorite: PokemonFavorite)

    @Query("DELETE FROM pokemon_favorites WHERE pokemonId = :id")
    suspend fun removePokemon(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM pokemon_favorites WHERE pokemonId = :id)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("SELECT * FROM pokemon_favorites")
    suspend fun getAllPokemons(): List<PokemonFavorite>
}
