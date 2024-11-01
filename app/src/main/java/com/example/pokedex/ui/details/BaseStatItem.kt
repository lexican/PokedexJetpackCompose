package com.example.pokedex.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.theme.darkGreen
import com.example.pokedex.ui.theme.darkPink
import com.example.pokedex.ui.theme.darkYellow
import com.example.pokedex.utils.capitalizeAllWordsSepByDash

@Composable
fun BaseStatItem(title: String, statValue: Int) {
    val value = (statValue * 0.01).coerceIn(0.0, 1.0)

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 0.dp)
            .fillMaxWidth()
    ) {
        Text(buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W400, color = Color(0xFF6B6B6B), fontSize = 14.sp
                )
            ) {
                append(title.capitalizeAllWordsSepByDash())
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W500, color = Color(0xFF161A33), fontSize = 14.sp
                )
            ) {
                append(" $statValue")
            }
        })

        //Spacer(modifier = Modifier.height(12.dp))

        val trackColor = when {
            value >= 0.67f -> darkGreen
            value > 0.33f -> darkYellow
            else -> darkPink
        }

        Slider(
            value = value.toFloat(),
            onValueChange = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp),
            colors = SliderDefaults.colors(
                disabledActiveTrackColor = trackColor, thumbColor = Color.Transparent
            )
        )
    }
}