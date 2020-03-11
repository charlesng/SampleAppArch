package com.cn29.aac.datasource.itunes.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.cn29.aac.repo.itunes.Album
import com.cn29.aac.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AlbumDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var itunesDb: ItunesDb
    private lateinit var albumDao: AlbumDao
    private lateinit var getAlbumLiveData: LiveData<List<Album>>
    private lateinit var getAllLiveData: LiveData<List<Album>>


    @Before
    fun setUp() {
        itunesDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                                                ItunesDb::class.java).build()
        albumDao = itunesDb.albumDao()
    }

    @After
    fun tearDown() {
        itunesDb.close()
    }

    @Test
    fun should_insert_1_Album() {
        val albums = generateDummyAlbum(size = 1)
        givenAlbumInserted(albums)
        whenAlbumFetch(artistId = 1)
        thenAlbumShouldMatch(expect = listOf(albums.first()),
                             actual = LiveDataTestUtil.getValue(getAlbumLiveData))
    }

    @Test
    fun should_get_all_Album() {
        val albums = generateDummyAlbum(size = 4)
        givenAlbumInserted(albums)
        whenAllAlbumFetch()
        thenAlbumShouldMatch(expect = albums,
                             actual = LiveDataTestUtil.getValue(getAllLiveData))
    }

    @Test
    fun should_update_1_album() {
        val albums = generateDummyAlbum(size = 1)
        givenAlbumInserted(albums)
        val updatedAlbum = albums.first().apply {
            primaryGenreName = "New Genre Name"
        }
        whenAlbumUpdate(updatedAlbum)
        whenAlbumFetch(albums.first().artistId)
        thenAlbumShouldMatch(expect = listOf(updatedAlbum),
                             actual = LiveDataTestUtil.getValue(getAlbumLiveData))
    }

    private fun whenAlbumUpdate(updatedAlbum: Album) {
        albumDao.update(updatedAlbum)
    }

    private fun whenAllAlbumFetch() {
        getAllLiveData = albumDao.getAll()
    }

    private fun thenAlbumShouldMatch(expect: List<Album>,
                                     actual: List<Album>) {
        assertEquals(expect, actual)
    }

    private fun whenAlbumFetch(artistId: Long) {
        getAlbumLiveData = albumDao.getAlbum(artistId)
    }

    private fun givenAlbumInserted(album: List<Album>) {
        albumDao.insert(album)
    }

    private fun generateDummyAlbum(size: Int): List<Album> {
        return (1..size).map {
            Album(artistId = it.toLong(),
                  collectionName = "Collection$it",
                  collectionPrice = 1.0,
                  primaryGenreName = "No Primary")
        }
    }
}