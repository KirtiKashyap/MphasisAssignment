package com.example.mphasisassignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mphasisassignment.model.Album

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    fun getAllAlbum() : LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<Album>)
    @Query("SELECT * FROM albums WHERE id = :id")
    fun getAlbum(id: String): LiveData<Album>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(albums: Album)
}