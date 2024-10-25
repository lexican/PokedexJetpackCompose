package com.example.pokedex.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.theme.inactiveTabColor
import com.example.pokedex.ui.theme.primaryColor

@Composable
fun HomeTabTitle(title: String, selected: Boolean) {
    val textColor = if (selected) primaryColor else inactiveTabColor
    val fontWeight = if (selected) FontWeight.W500 else FontWeight.W400
    Row {
        Text(
            title, fontSize = 16.sp, fontWeight = fontWeight, color = textColor
        )

    }
}