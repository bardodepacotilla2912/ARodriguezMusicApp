package com.bdp.arodriguezmusicapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun RecentItemCard(imageUrl: String, title: String, artist: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(8.dp)))
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(artist, style = MaterialTheme.typography.bodyMedium)
                    Text("  • Popular Song", style = MaterialTheme.typography.bodyMedium)
                }
                            }

            AsyncImage(
                model = "file:///android_asset/menu.png",
                contentDescription = "Play",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
@Preview
fun RecentItemCardPreview() {
    RecentItemCard(
        imageUrl = "file:///android_asset/song_cover.png",
        title = "Como la flor",
        artist = "Selena Quintanilla"
    ) {}
}