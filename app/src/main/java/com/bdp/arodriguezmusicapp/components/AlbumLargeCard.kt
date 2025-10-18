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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
        // Box for the gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xFF6A1B9A)),
                        startY = 300f
                    )
                )
        )
        // Box for the content at the bottom
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Text(title, color = Color.White)
                Text(artist, color = Color.White.copy(alpha = 0.8f))
            }
            FilledIconButton(
                onClick = onClick,
                modifier = Modifier.align(Alignment.BottomEnd),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color.White.copy(alpha = 0.3f)
                )) {

                AsyncImage(
                    model = "file:///android_asset/jugar.png",
                    contentDescription = "Play",

                )

            }
        }
    }
}

@Composable
@Preview
fun AlbumLargeCardPreview(){
    AlbumLargeCard(
        imageUrl = "https://picsum.photos/400/300",
        title = "Album Title",
        artist = "Artist Name",
        onClick = {}
    )
}