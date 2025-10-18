package com.bdp.arodriguezmusicapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
        Row(Modifier.padding(16.dp)) {
            AsyncImage(model = imageUrl, contentDescription = title, modifier = Modifier.size(52.dp).clip(RoundedCornerShape(8.dp)))
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(artist, style = MaterialTheme.typography.bodyMedium)
            }
           // Icon(Icons.Default.MoreVert, contentDescription = null)
        }
    }
}

