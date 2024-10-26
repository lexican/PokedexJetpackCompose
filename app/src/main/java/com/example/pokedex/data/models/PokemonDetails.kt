package com.example.pokedex.data.models

import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    val id: Int? = null,
    val name: String? = null,
    @SerializedName("sprites") val sprites: Sprites? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val types: List<Type>? = null,
    val stats: List<Stat>? = null
) {
    data class Sprites(
        @SerializedName("other") val other: Other? = null
    ) {
        data class Other(
            @SerializedName("official-artwork") val officialArtwork: OfficialArtwork? = null
        ) {
            data class OfficialArtwork(
                @SerializedName("front_default") val frontDefault: String? = null
            )
        }
    }

    data class Type(
        @SerializedName("type") val type: TypeDetail
    ) {
        data class TypeDetail(
            val name: String
        )
    }

    data class Stat(
        val title: String, val value: Int
    )
}
