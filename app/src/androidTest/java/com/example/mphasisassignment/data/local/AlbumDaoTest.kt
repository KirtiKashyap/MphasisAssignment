package com.example.mphasisassignment.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.mphasisassignment.getOrAwaitValue
import com.example.mphasisassignment.model.Album
import dagger.hilt.android.testing.HiltAndroidRule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class AlbumDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Inject
    @Named("test_db")
    lateinit var database: LocalDataBase
    private lateinit var albumDao: AlbumDao

    @Before
    fun setup() {
        hiltRule.inject()
        albumDao = database.albumDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAlbum() = runBlockingTest {
        val album = Album(
            title = "title test",
            userId = "2",
            id = "1"
        )
        albumDao.insertAlbum(album)
        val allUsers = albumDao.getAllAlbum().getOrAwaitValue()
        assertThat(allUsers).contains(album)
    }
}