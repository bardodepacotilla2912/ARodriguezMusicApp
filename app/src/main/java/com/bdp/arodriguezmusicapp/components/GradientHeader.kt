package com.bdp.arodriguezmusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun GradientHeader(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(listOf(Color(0xFFB388FF), Color(0xFF7E57C2)))
            )
            .padding(20.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text("Good Morning!", color = Color.White.copy(alpha = 0.8f))
            Text(name, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
    }
}