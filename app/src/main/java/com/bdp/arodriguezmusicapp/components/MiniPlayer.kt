package com.bdp.arodriguezmusicapp.components



import androidx.compose.foundation.background
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
        AsyncImage(model = imageUrl, contentDescription = title, modifier = Modifier.size(44.dp).clip(RoundedCornerShape(8.dp)))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = Color.White)
            Text(artist, color = Color.White.copy(alpha = 0.8f))
        }
        FilledIconButton(onClick = { playing = !playing }, colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White)) {
           // Icon(if (playing) Icons.Filled.Pause else Icons.Filled.PlayArrow, contentDescription = null)
        }
    }
}