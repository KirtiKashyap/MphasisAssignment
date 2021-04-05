package com.example.mphasisassignment.repository

import com.example.mphasisassignment.data.local.AlbumDao
import com.example.mphasisassignment.data.remote.RemoteDataSource
import javax.inject.Inject

class AlbumRepository@Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: AlbumDao){

}