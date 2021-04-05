package com.example.mphasisassignment.di

import android.content.Context
import com.example.mphasisassignment.data.local.AlbumDao
import com.example.mphasisassignment.data.local.LocalDataBase
import com.example.mphasisassignment.data.remote.RemoteDataSource
import com.example.mphasisassignment.data.remote.ApiService
import com.example.mphasisassignment.repository.AlbumRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAlbumService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideAlbumRemoteDataSource(characterService: ApiService) = RemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = LocalDataBase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideAlbumDao(db: LocalDataBase) = db.albumDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,
                          localDataSource: AlbumDao
    ) =
        AlbumRepository(remoteDataSource, localDataSource)
}