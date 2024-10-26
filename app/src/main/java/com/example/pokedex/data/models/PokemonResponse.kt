package com.example.pokedex.data.models

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("results") val results: List<Pokemon>
)