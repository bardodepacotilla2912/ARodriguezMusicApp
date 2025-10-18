package com.bdp.arodriguezmusicapp.components

// Este archivo define un Composable `MiniPlayer` para mostrar un reproductor de música en miniatura.


import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Pause
//import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage



@Composable
fun MiniPlayer(imageUrl: String?, title: String, artist: String) {
    var playing by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF2A1742))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // `AsyncImage` carga y muestra una imagen desde una URL de forma asíncrona.
        AsyncImage(model = imageUrl, contentDescription = title, modifier = Modifier.size(44.dp).clip(RoundedCornerShape(8.dp)))
        // `Spacer` añade un espacio horizontal fijo.
        Spacer(Modifier.width(12.dp))
        // `Column` organiza sus elementos hijos verticalmente y ocupa el espacio restante gracias a `weight(1f)`.
        Column(Modifier.weight(1f)) {
            // Muestra el título de la canción en color blanco.
            Text(title, color = Color.White)
            // Muestra el nombre del artista en un color blanco semitransparente.
            Text(artist, color = Color.White.copy(alpha = 0.8f))
        }
        // Un botón de icono con fondo. El `onClick` alterna el estado `playing`.
        FilledIconButton(onClick = { playing = !playing }, colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White)) {
           // El código para mostrar el icono de reproducción/pausa está comentado.
           // Icon(if (playing) Icons.Filled.Pause else Icons.Filled.PlayArrow, contentDescription = null)
           AsyncImage(
               model = "file:///android_asset/jugar.png",
               contentDescription = "Play",
               modifier = Modifier.size(24.dp)
           )
        }
    }
}

@Composable
@Preview
fun MiniPlayerPreview() {

    MiniPlayer(
        imageUrl = "https://i.scdn.co/image/ab67616d0000b2734718e2b12e5a403be450f9c2",
        title = "Blinding Lights",
        artist = "The Weeknd"
    )
}