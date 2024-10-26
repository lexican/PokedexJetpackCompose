package com.example.pokedex.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.models.ApiResponse
import com.example.pokedex.data.models.PokemonDetails
import com.example.pokedex.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _pokemonListState =
        MutableStateFlow<ApiResponse<List<PokemonDetails>>>(ApiResponse.Loading)
    val pokemonListState: StateFlow<ApiResponse<List<PokemonDetails>>> = _pokemonListState

    fun getPokemonList(limit: Int, offset: Int) {
        viewModelScope.launch {
            repository.getPokemonList(limit, offset).collect { response ->
                _pokemonListState.value = response
            }
        }
    }
}
