package com.cn29.aac.datasource.itunes.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.cn29.aac.repo.itunes.Artist
import com.cn29.aac.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4ClassRunner::class)
class ArtistDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var itunesDb: ItunesDb
    private lateinit var artistDao: ArtistDao
    private lateinit var getArtistsLiveData: LiveData<List<Artist>>

    @Before
    fun setUp() {
        itunesDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                                                ItunesDb::class.java).build()
        artistDao = itunesDb.artistDao()
    }

    @After
    fun tearDown() {
        itunesDb.close()
    }

    @Test
    fun should_insert_1_Artist() {
        val artists = generateDummyArtist(size = 1)
        givenArtistInserted(artists)
        whenArtistsFetch(query = "Artist")
        thenArtistShouldBeFetch(expect = artists,
                                actual = LiveDataTestUtil.getValue(
                                        getArtistsLiveData))
    }

    @Test
    fun should_update_1_artist() {
        val artists = generateDummyArtist(size = 1)
        givenArtistInserted(artists)
        val updatedArtist = artists.first().apply {
            trackName = "New TrackName"
        }
        whenArtistUpdate(updatedArtist)
        whenArtistsFetch(query = "Artist")
        thenArtistShouldBeFetch(expect = listOf(updatedArtist),
                                actual = LiveDataTestUtil.getValue(
                                        getArtistsLiveData))
    }

    private fun whenArtistUpdate(updatedArtist: Artist) {
        artistDao.update(updatedArtist)
    }

    private fun thenArtistShouldBeFetch(expect: List<Artist>,
                                        actual: List<Artist>) {
        assertEquals(expect, actual)
    }

    private fun whenArtistsFetch(query: String) {
        getArtistsLiveData = artistDao.getArtists(query)
    }

    private fun givenArtistInserted(artists: List<Artist>) {
        artistDao.insert(*artists.toTypedArray())
    }

    private fun generateDummyArtist(size: Int): List<Artist> {
        return (1..size).map {
            Artist(artistId = it.toLong(),
                   artistName = "Artist$it",
                   trackName = "TrackName$it",
                   artworkUrl100 = "",
                   trackPrice = 1.0,
                   isFavourite = false,
                   createDate = Date())
        }
    }
}