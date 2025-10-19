package com.bdp.arodriguezmusicapp.screens

// 1. IMPORTS AÑADIDOS: Clases necesarias que no estaban importadas.
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.bdp.arodriguezmusicapp.R
import com.bdp.arodriguezmusicapp.components.MiniPlayer
import com.bdp.arodriguezmusicapp.models.Album




@Composable
fun DetailScreen(id: String) {
    // Se especifica la factoría para el ViewModel si es necesario
    val vm: DetailViewModel = viewModel()
    val state by vm.album.collectAsState()

    // Este efecto se ejecuta cuando la pantalla aparece por primera vez (o si el id cambia)
    LaunchedEffect(id) {
        vm.load(id)
    }

    Scaffold(
        containerColor = Color(0xFFF4EDFF), // fondo lila claro
        bottomBar = {
            // Se extraen los datos del álbum de forma segura
            val albumData = (state as? UiState.Success<Album>)?.data
            MiniPlayer(
                imageUrl = albumData?.image,
                // Si los datos no están listos, se usan valores por defecto para evitar que se vea vacío
                title = albumData?.title ?: "Cargando...",
                artist = albumData?.artist ?: ""
            )
        }
    ) { padding ->
        // El when maneja los diferentes estados de la UI: carga, error o éxito
        when (val currentState = state) { // 2. MEJORA: Se asigna 'state' a una variable para evitar casts repetidos
            is UiState.Loading -> {
                // Muestra un indicador de progreso centrado
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Error -> {
                // Muestra un mensaje de error centrado
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = currentState.message, // Muestra el mensaje de error real
                        modifier = Modifier.padding(20.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            is UiState.Success -> {
                // Si la carga fue exitosa, muestra los detalles
                val album = currentState.data
                LazyColumn(
                    modifier = Modifier
                        .padding(padding) // Usa el padding del Scaffold
                        .fillMaxSize()
                ) {
                    item {
                        // El contenido se mantiene igual, ya estaba bien estructurado
                        AlbumDetailsContent(album)
                    }
                    // 4. CORRECCIÓN: Se mueve la lista de 'items' aquí, dentro del LazyColumn.
                    // `items` solo puede ser llamado dentro del scope de un LazyColumn.
                    items(10) { index ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = album.image,
                                    contentDescription = album.title,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(RoundedCornerShape(10.dp)),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${album.title} • Track ${index + 1}",
                                        color = Color.Black,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                                    )
                                    Text(
                                        text = album.artist,
                                        color = Color.Gray
                                    )
                                }

                                AsyncImage(
                                    model = "file:///android_asset/menu.png",
                                    contentDescription = "Menu",
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// 3. MEJORA: Se extrae el contenido a un Composable separado para mayor limpieza
@Composable
private fun AlbumDetailsContent(album: Album) {
    // Imagen superior del álbum
    Box(
        modifier = Modifier
            .padding(20.dp)
            .aspectRatio(1f) // Esto hace que el Box sea cuadrado
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 40.dp))
    ) {
        AsyncImage(
            model = album.image,
            contentDescription = album.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        // Capa oscura para legibilidad del texto
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color(0x802A1742))
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text(
                text = album.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = album.artist,
                color = Color.White.copy(alpha = .9f),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    Spacer(Modifier.height(20.dp))

    // Descripción del álbum
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "About this album",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF4A148C)
            )
            Spacer(Modifier.height(8.dp))
            // Se usa la descripción de la API o un texto por defecto si es nula
            Text(
                text = album.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF333333),
                textAlign = TextAlign.Justify
            )
        }
    }

    Spacer(Modifier.height(20.dp))

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Artist: ",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF4A148C),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = album.artist,
                color = Color(0xFF4A148C).copy(alpha = 0.8f)
            )
        }
    }

    Spacer(Modifier.height(30.dp))

}

