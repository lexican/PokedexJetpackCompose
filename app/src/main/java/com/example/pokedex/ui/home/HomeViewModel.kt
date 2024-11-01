package com.example.pokedex.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.models.ApiResponse
import com.example.pokedex.data.models.PokemonDetails
import com.example.pokedex.data.repository.OfflinePokemonRepositoryImpl
import com.example.pokedex.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository, @ApplicationContext private val context: Context,
    private val offlinePokemonRepository: OfflinePokemonRepositoryImpl,
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLoadingMore = MutableStateFlow<Boolean>(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore


    private val _hasMore = MutableStateFlow<Boolean>(true)
    private val _isError = MutableStateFlow<Boolean>(false)

    private val _selectedPokemon = MutableStateFlow<PokemonDetails?>(null)
    val selectedPokemon: StateFlow<PokemonDetails?> get() = _selectedPokemon

    val isError: StateFlow<Boolean> = _isError


    private val _limit = 27
    private var offset = 0

    private val _pokemonListState = MutableStateFlow<List<PokemonDetails>>(emptyList())
    val pokemonListState: StateFlow<List<PokemonDetails>> = _pokemonListState

    private val _favouritePokemons = MutableStateFlow(setOf<PokemonDetails>())
    val favouritePokemons: StateFlow<Set<PokemonDetails>> = _favouritePokemons

    init {
        loadInitialPokemonList()
        loadPokemonFromDb()
    }

    private fun loadInitialPokemonList() {
        _isError.value = false
        _isLoading.value = true
        getPokemonList(offset)
    }

    private fun loadPokemonFromDb() {
        val pokemons: MutableSet<Int> = mutableSetOf()
        viewModelScope.launch {
            offlinePokemonRepository.getAllPokemons().collect { savedPokes ->
                pokemons.addAll(savedPokes.map { it.pokemonId })
            }
            repository.getSavedPokemons(pokemons).collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        _favouritePokemons.value = response.data.toSet()
                    }

                    is ApiResponse.Error -> {
                        Log.e("HomeViewModel", "Error ${response.message}")
                    }
                }
            }
        }
    }

    fun loadMorePokemon() {
        if (_isLoadingMore.value || !_hasMore.value) return
        _isLoadingMore.value = true
        _isError.value = false
        getPokemonList(offset)
    }

    fun retry() {
        if (_pokemonListState.value.isEmpty()) {
            loadInitialPokemonList()
        } else {
            loadMorePokemon();
        }
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
                        Log.e("HomeViewModel", "Error ${response.message}")
                    }
                }
            }
        }
    }

    fun toggleFavouritePokemon(pokemon: PokemonDetails?) {
        pokemon?.let { nonNullPokemon ->
            val currentFavourites = _favouritePokemons.value.toMutableSet()
            if (_favouritePokemons.value.contains(nonNullPokemon)) {
                currentFavourites.remove(nonNullPokemon)
                viewModelScope.launch {
                    pokemon.id?.let { offlinePokemonRepository.removeFavorite(it) }
                }
            } else {
                currentFavourites.add(nonNullPokemon)
                viewModelScope.launch {
                    pokemon.id?.let { offlinePokemonRepository.addFavorite(it) }
                }
            }
            _favouritePokemons.value = currentFavourites
        }
    }


    fun isFavorite(pokemon: PokemonDetails): Boolean {
        val isFavourite = _favouritePokemons.value.any { it.id == pokemon.id }
        return isFavourite
    }

    fun setSelectedPokemon(pokemon: PokemonDetails) {
        _selectedPokemon.value = pokemon
    }
}
