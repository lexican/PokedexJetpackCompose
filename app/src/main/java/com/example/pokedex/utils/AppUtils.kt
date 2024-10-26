package com.example.pokedex.utils

import com.example.pokedex.data.models.PokemonDetails

fun convertToIdHash(id: Int): String {
    require(id >= 0) { "ID should be greater than or equal to 0" }
    return "#${id.toString().padStart(3, '0')}"
}

fun convertPokemonTypesToString(typesList: List<PokemonDetails.Type>): String {
    return typesList.joinToString(", ") { it.type.name }
}