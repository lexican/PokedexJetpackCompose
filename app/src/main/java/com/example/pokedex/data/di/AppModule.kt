package com.example.pokedex.data.di

import com.example.pokedex.data.service.ApiService
import com.example.pokedex.data.repository.Repository
import com.example.pokedex.data.repository.RepositoryImpl
import com.example.pokedex.data.service.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}
