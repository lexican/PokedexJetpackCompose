package com.example.pokedex.data.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.data.dao.PokemonDao
import com.example.pokedex.data.dao.PokemonDatabase
import com.example.pokedex.data.repository.OfflinePokemonRepositoryImpl
import com.example.pokedex.data.service.ApiService
import com.example.pokedex.data.repository.Repository
import com.example.pokedex.data.repository.RepositoryImpl
import com.example.pokedex.data.service.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providePokemonRepository(apiService: ApiService): Repository = RepositoryImpl(apiService)

    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context, PokemonDatabase::class.java, "item_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(database: PokemonDatabase): PokemonDao {
        return database.pokemonDao()
    }

    @Provides
    @Singleton
    fun provideOfflinePokemonRepository(pokemonFavoriteDao: PokemonDao): OfflinePokemonRepositoryImpl {
        return OfflinePokemonRepositoryImpl(pokemonFavoriteDao)
    }
}
