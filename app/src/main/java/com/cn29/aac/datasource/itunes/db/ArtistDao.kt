package com.cn29.aac.datasource.itunes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cn29.aac.repo.itunes.Artist

@Dao
abstract class ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg artists: Artist?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(artists: List<Artist?>?)

    @Query("SELECT * FROM artist WHERE artistName like '%' || :query  || '%'")
    abstract fun getArtists(query: String?): LiveData<List<Artist?>?>?

    @Update
    abstract fun update(artist: Artist?): Int
}