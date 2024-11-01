package com.example.pokedex.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.data.models.PokemonFavorite

@Database(entities = [PokemonFavorite::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}