package com.bdp.arodriguezmusicapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bdp.arodriguezmusicapp.components.*
import com.bdp.arodriguezmusicapp.models.Album


@Composable
fun HomeScreen(onOpenAlbum: (Int) -> Unit) {
    val vm: HomeViewModel = viewModel()
    val state by vm.albums.collectAsState()


    Scaffold(
        bottomBar = {
            MiniPlayer(
                imageUrl = (state as? UiState.Success<List<Album>>)?.data?.firstOrNull()?.image,
                title = (state as? UiState.Success<List<Album>>)?.data?.firstOrNull()?.title ?: "Tales of Ithiria",
                artist = (state as? UiState.Success<List<Album>>)?.data?.firstOrNull()?.artist ?: "Haggard"
            )
        }
    ) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            item { GradientHeader(name = "Abraham Rodríguez") }
            item { SectionTitle("Albums") }
            item {

                when (state) {
                    is UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth().padding(16.dp))
                    is UiState.Error -> Text("Error loading albums", Modifier.padding(16.dp))
                    is UiState.Success -> {
                        val albums = (state as UiState.Success<List<Album>>).data
                        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(albums) { a ->
                                AlbumLargeCard(a.image, a.title, a.artist) { onOpenAlbum(a.id) }
                            }
                        }
                    }
                }
            }
            item { Spacer(Modifier.height(16.dp)); SectionTitle("Recently Played") }
            if (state is UiState.Success) {
                val albums = (state as UiState.Success<List<Album>>).data
                items(albums) { a ->
                    RecentItemCard(a.image, a.title, a.artist) { onOpenAlbum(a.id) }
                    Spacer(Modifier.height(12.dp))
                }
            }
            item { Spacer(Modifier.height(88.dp)) }
        }
    }
}

private fun LazyItemScope.onOpenAlbum(p1: String) {
    TODO("Not yet implemented")
}


@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))
}