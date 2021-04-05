package com.example.mphasisassignment.repository

import com.example.mphasisassignment.data.local.AlbumDao
import com.example.mphasisassignment.data.remote.RemoteDataSource
import com.example.mphasisassignment.utils.performGetOperation
import javax.inject.Inject

class AlbumRepository@Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: AlbumDao) {
        fun getAlbums() = performGetOperation(
            databaseQuery = { localDataSource.getAllAlbum() },
            networkCall = { remoteDataSource.getAlbums() },
            saveCallResult = { localDataSource.insertAll(it) }
        )
    fun getAlbumById(id: String) = localDataSource.getAlbum(id)
}
