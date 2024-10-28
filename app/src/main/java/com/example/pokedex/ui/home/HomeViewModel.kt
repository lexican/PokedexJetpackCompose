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

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLoadingMore = MutableStateFlow<Boolean>(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore


    private val _hasMore = MutableStateFlow<Boolean>(true)
    private val _isError = MutableStateFlow<Boolean>(false)


    private val _limit = 27
    private var offset = 0

    private val _pokemonListState = MutableStateFlow<List<PokemonDetails>>(emptyList())
    val pokemonListState: StateFlow<List<PokemonDetails>> = _pokemonListState

    init {
        loadInitialPokemonList()
    }

    private fun loadInitialPokemonList() {
        _isError.value = false
        _isLoading.value = true
        getPokemonList(offset)
    }

    fun loadMorePokemon() {
        if (_isLoadingMore.value || !_hasMore.value) return // Prevent duplicate loads
        _isLoadingMore.value = true
        _isError.value = false
        getPokemonList(offset)
    }

    private fun getPokemonList(offset: Int) {
        viewModelScope.launch {
            repository.getPokemonList(_limit, offset).collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        val newList = response.data
                        val currentList = _pokemonListState.value
                        //
                        _pokemonListState.value = currentList + newList
                        _isLoading.value = false
                        _isLoadingMore.value = false
                        this@HomeViewModel.offset += _limit
                        _hasMore.value = newList.isNotEmpty()
                    }

                    is ApiResponse.Error -> {
                        _isError.value = true
                        _isLoading.value = false
                        _isLoadingMore.value = false
                        //response.message
                    }
                }
            }
        }
    }
}
