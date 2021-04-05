package com.example.mphasisassignment.data.remote

import com.example.mphasisassignment.model.Album
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>
}