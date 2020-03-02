package com.cn29.aac.datasource.itunes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cn29.aac.repo.itunes.Album

@Dao
abstract class AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg artists: Album?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(artists: List<Album?>?)

    @get:Query("SELECT * FROM album")
    abstract val all: LiveData<List<Album?>?>?

    @Query("SELECT * FROM album WHERE artistId = :artistId ")
    abstract operator fun get(artistId: Int): LiveData<List<Album?>?>?

    @Update
    abstract fun update(artist: Album?): Int
}