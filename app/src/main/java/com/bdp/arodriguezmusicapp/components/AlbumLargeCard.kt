package com.bdp.arodriguezmusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun AlbumLargeCard(imageUrl: String, title: String, artist: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(260.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
    ) {
        AsyncImage(model = imageUrl, contentDescription = title, modifier = Modifier.fillMaxSize())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x66000000))
                .padding(16.dp)
        ) {
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(title, color = Color.White)
                Text(artist, color = Color.White.copy(alpha = 0.8f))
            }
            FilledIconButton(onClick = onClick, modifier = Modifier.align(Alignment.CenterEnd)) {
              //  Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
            }
        }
    }
}