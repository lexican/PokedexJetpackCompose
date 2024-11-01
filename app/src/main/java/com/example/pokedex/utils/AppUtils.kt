package com.example.pokedex.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.pokedex.data.models.PokemonDetails
import java.util.Locale

fun convertToIdHash(id: Int): String {
    require(id >= 0) { "ID should be greater than or equal to 0" }
    return "#${id.toString().padStart(3, '0')}"
}

fun convertPokemonTypesToString(typesList: List<PokemonDetails.Type>): String {
    return typesList.joinToString(", ") { it.type.name }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val network = it.activeNetwork
        val networkCapabilities = it.getNetworkCapabilities(network)
        return networkCapabilities != null && networkCapabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
    }
    return false
}

fun calculateBMI(height: Double, weight: Double): String {
    return String.format(Locale.US, "%.2f", weight / (height * height))
}

fun calculateAvgPower(stats: List<PokemonDetails.Stat>): Double {
    return if (stats.isNotEmpty()) {
        stats.map { it.baseStat }.average()
    } else {
        0.0
    }
}

fun String.capitalizeAllWordsSepByDash(): String {
    return this.split("-").joinToString("-") { word ->
        word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }
}