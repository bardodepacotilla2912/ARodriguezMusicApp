package com.bdp.arodriguezmusicapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
//import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bdp.arodriguezmusicapp.components.*
import com.bdp.arodriguezmusicapp.models.Album

@Composable
fun HomeScreen(onOpenAlbum: (String) -> Unit) {
    val vm: HomeViewModel = viewModel()
    val state by vm.albums.collectAsState()

    Scaffold(
        containerColor = Color(0xFFF5F0FF), // Fondo general lila claro
        bottomBar = {
            MiniPlayer(
                imageUrl = (state as? UiState.Success<List<Album>>)?.data?.firstOrNull()?.image,
                title = (state as? UiState.Success<List<Album>>)?.data?.firstOrNull()?.title ?: "Tales of Ithiria",
                artist = (state as? UiState.Success<List<Album>>)?.data?.firstOrNull()?.artist ?: "Haggard"
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(28.dp)) // <-- Bordes redondeados
                .background(Color.White)         // <-- Fondo blanco dentro
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(28.dp)) // <-- Bordes redondeados
            ) {
                item { GradientHeader(name = "Abraham Rodríguez") }
                item { SectionTitle("Albums") }
                when (state) {
                    is UiState.Loading -> item {
                        LinearProgressIndicator(Modifier.fillMaxWidth().padding(16.dp))
                    }
                    is UiState.Error -> item {
                        Text("Error loading albums", Modifier.padding(16.dp))
                    }
                    is UiState.Success -> {
                        val albums = (state as UiState.Success<List<Album>>).data
                        item {
                            LazyRow(
                                contentPadding = PaddingValues(vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(albums) { a ->
                                    AlbumLargeCard(a.image, a.title, a.artist) { onOpenAlbum(a.id) }
                                }
                            }
                        }
                        item { Spacer(Modifier.height(16.dp)); SectionTitle("Recently Played") }
                        items(albums) { a ->
                            RecentItemCard(a.image, a.title, a.artist) { onOpenAlbum(a.id) }
                            Spacer(Modifier.height(12.dp))
                        }
                        item { Spacer(Modifier.height(80.dp)) }
                    }
                }
            }
        }
    }
}




@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen {}
}