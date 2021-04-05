package com.example.mphasisassignment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mphasisassignment.model.Album

@Database(entities = [Album::class], version = 1, exportSchema = false)
abstract class LocalDataBase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object {
        @Volatile
        private var instance: LocalDataBase? = null

        fun getDatabase(context: Context): LocalDataBase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, LocalDataBase::class.java, "albums")
                .fallbackToDestructiveMigration()
                .build()
    }
}