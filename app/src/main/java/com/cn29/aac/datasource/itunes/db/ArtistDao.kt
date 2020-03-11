package com.cn29.aac.datasource.itunes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cn29.aac.repo.itunes.Artist

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg artists: Artist?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artists: List<Artist?>?)

    @Query("SELECT * FROM artist WHERE artistName like '%' || :query  || '%'")
    fun getArtists(query: String): LiveData<List<Artist>>

    @Update
    fun update(artist: Artist): Int
}