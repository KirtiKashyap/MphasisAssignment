package com.example.mphasisassignment.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mphasisassignment.repository.AlbumRepository

class AlbumViewModel @ViewModelInject constructor(
    private val albumRepository: AlbumRepository
) : ViewModel() {
    val albums = albumRepository.getAlbums()
}