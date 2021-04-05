package com.example.mphasisassignment.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
): BaseDataSource () {

    suspend fun getAlbums() = getResult { apiService.getAlbums() }

}