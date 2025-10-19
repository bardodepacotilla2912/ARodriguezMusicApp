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


sealed interface UiState<out T> {
    object Loading : UiState<Nothing>
    data class Error(val message: String) : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
}


class HomeViewModel : ViewModel() {
    private val _albums = MutableStateFlow<UiState<List<Album>>>(UiState.Loading)
    val albums: StateFlow<UiState<List<Album>>> = _albums


    init { fetch() }


    fun fetch() = viewModelScope.launch {
        _albums.value = UiState.Loading
        try {
            val list = RetrofitClient.api.getAlbums()
            _albums.value = UiState.Success(list)
        } catch (e: Exception) {
            _albums.value = UiState.Error(e.localizedMessage ?: "Error")
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview2() {
    HomeScreen {}
}