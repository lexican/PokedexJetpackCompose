package com.example.pokedex.utils

import androidx.compose.ui.graphics.Color

val pokemonImageBackgroundColors = listOf(
    Color(0xffB692F6).copy(alpha = 0.5f),
    Color(0xffFFAED7).copy(alpha = 0.5f),
    Color(0xff6CE9A6).copy(alpha = 0.5f),
    Color(0xffF65D97).copy(alpha = 0.5f),
    Color.Black.copy(alpha = 0.5f),
    Color(0xffFFC200).copy(alpha = 0.5f),
    Color(0xff8BF9F3).copy(alpha = 0.5f),
    Color(0xffE9FC52).copy(alpha = 0.5f)
)

val pokemonImageFirstGroup = listOf("A", "B", "C", "D")
val pokemonImageSecondGroup = listOf("E", "F", "G", "H")
val pokemonImageThirdGroup = listOf("I", "J", "K")
val pokemonImageFourthGroup = listOf("L", "M", "N")
val pokemonImageFifthGroup = listOf("O", "P", "Q")
val pokemonImageSixthGroup = listOf("R", "S", "T")
val pokemonImageSeventhGroup = listOf("U", "V", "W")
val pokemonImageEighthGroup = listOf("X", "Y", "Z")

val pokemonImageAlphabetGroup = listOf(
    pokemonImageFirstGroup,
    pokemonImageSecondGroup,
    pokemonImageThirdGroup,
    pokemonImageFourthGroup,
    pokemonImageFifthGroup,
    pokemonImageSixthGroup,
    pokemonImageSeventhGroup,
    pokemonImageEighthGroup
)

fun getPokemonImageBackgroundColor(character: String): Color {
    // Default to a random color
    var result = pokemonImageBackgroundColors.random()

    for ((index, group) in pokemonImageAlphabetGroup.withIndex()) {
        if (group.contains(character.uppercase())) {
            result = pokemonImageBackgroundColors[index]
            break
        }
    }

    return result
}
