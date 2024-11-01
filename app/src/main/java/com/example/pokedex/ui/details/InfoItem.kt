package com.example.pokedex.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.theme.inactiveTabColor
import com.example.pokedex.ui.theme.textColor

@Composable
fun InfoItem(title: String, value: String) {
    Box(modifier = Modifier.padding(end = 40.dp)) {
        Column() {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                color = inactiveTabColor,
            )
            Spacer(modifier = Modifier.height(height = 4.dp))
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = textColor,
            )
        }
    }
}