package com.bdp.arodriguezmusicapp.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.bdp.arodriguezmusicapp.models.Album
import com.bdp.arodriguezmusicapp.components.MiniPlayer


@Composable
fun DetailScreen(id: String) {
    val vm: DetailViewModel = viewModel()
    val state by vm.album.collectAsState()


    LaunchedEffect(id) { vm.load(id) }


    Scaffold(
        bottomBar = {
            val a = (state as? UiState.Success<Album>)?.data
            MiniPlayer(
                imageUrl = a?.image,
                title = a?.title ?: "Tales of Ithiria",
                artist = a?.artist ?: "Haggard"
            )
        }
    ) { padding ->
        when (state) {
            is UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth().padding(20.dp))
            is UiState.Error -> Text("Error", Modifier.padding(20.dp))
            is UiState.Success -> {
                val album = (state as UiState.Success<Album>).data
                LazyColumn(Modifier.padding(padding)) {
                    item {
                        Box(Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = album.image,
                                contentDescription = album.title,
                                modifier = Modifier.height(260.dp).fillMaxWidth()
                            )
                            Box(Modifier.matchParentSize().background(Color(0x802A1742)))
                            Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                                Text(
                                    album.title,
                                    color = Color.White,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Text(album.artist, color = Color.White.copy(alpha = .9f))
                            }
                        }
                    }
                }
            }
        }
    }
}