package com.bdp.arodriguezmusicapp.services

import com.bdp.arodriguezmusicapp.models.Album
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("api/albums")
    suspend fun getAlbums(): List<Album>


    @GET("api/albums/{id}")
    suspend fun getAlbum(@Path("id") id: String): Album
}