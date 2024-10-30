package com.example.pokedex.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.pokedex.data.models.PokemonDetails

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