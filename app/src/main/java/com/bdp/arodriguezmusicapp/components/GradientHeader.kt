package com.bdp.arodriguezmusicapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage // Import actualizado para Coil 3
import coil3.compose.rememberAsyncImagePainter // Import antiguo (puede eliminarse si no se usa)

@Composable
fun GradientHeader(name: String) {
    // 1. El Box principal actúa como contenedor con el fondo degradado
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 12.dp, end = 12.dp)
            .height(130.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8E24AA), // Morado claro arriba
                        Color(0xFF6A1B9A)  // Morado oscuro abajo
                    )
                )
            )
    ) {
        // --- DECORACIÓN ---

        // 2. Imagen de menú a la izquierda
        AsyncImage(
            model = "file:///android_asset/barra-de-menus.png",
            contentDescription = "Menú",
            modifier = Modifier
                .align(Alignment.TopStart) // Alinea en la esquina superior izquierda
                .padding(start = 20.dp, top = 20.dp)
                .size(24.dp) // Tamaño del icono
        )

        // 3. Imagen de lupa a la derecha
        AsyncImage(
            model = "file:///android_asset/lupa.png",
            contentDescription = "Buscar",
            modifier = Modifier
                .align(Alignment.TopEnd) // Alinea en la esquina superior derecha
                .padding(end = 20.dp, top = 20.dp)
                .size(24.dp) // Tamaño del icono
        )

        // --- CONTENIDO PRINCIPAL ---

        // 4. Texto centrado verticalmente
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart) // Alinea la columna completa al centro-izquierda
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Good Morning!",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 15.sp
            )
            Text(
                text = name,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview
fun GradientHeaderPreview() {
    GradientHeader("Abraham Rodríguez")
}
