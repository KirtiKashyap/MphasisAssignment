package com.example.mphasisassignment.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.mphasisassignment.model.Album
import com.example.mphasisassignment.repository.AlbumRepository

class DetailViewModel @ViewModelInject constructor(
    private val repository: AlbumRepository
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _album = _id.switchMap { id ->
        repository.getAlbumById(id)
    }
    val album: LiveData<Album> = _album


    fun start(id: String) {
        _id.value = id
    }

}