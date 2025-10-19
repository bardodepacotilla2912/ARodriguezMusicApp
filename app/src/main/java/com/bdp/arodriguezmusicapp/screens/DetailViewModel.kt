package com.bdp.arodriguezmusicapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bdp.arodriguezmusicapp.models.Album
import com.bdp.arodriguezmusicapp.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DetailViewModel : ViewModel() {
    private val _album = MutableStateFlow<UiState<Album>>(UiState.Loading)
    val album: StateFlow<UiState<Album>> = _album


    fun load(id: String) = viewModelScope.launch {
        _album.value = UiState.Loading
        try {
            _album.value = UiState.Success(RetrofitClient.api.getAlbum(id))
        } catch (e: Exception) {
            _album.value = UiState.Error(e.localizedMessage ?: "Error")
        }
    }
}

//@Composable
//@Preview
//fun DetailScreenPreview() {
//    DetailScreen(id = "1")
//}