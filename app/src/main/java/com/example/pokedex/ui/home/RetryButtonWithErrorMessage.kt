package com.example.pokedex.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.theme.primaryColor

@Composable
fun RetryButtonWithErrorMessage(onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "An error just occurred",
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(height = 8.dp))
        Button(
            onClick = {
                onClick()
            }, colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
        ) {
            Text(
                "Retry",
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                color = Color.White,
            )
        }
    }
}
