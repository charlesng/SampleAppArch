package com.cn29.aac.datasource.itunes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cn29.aac.repo.itunes.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg artists: Album?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artists: List<Album>)

    @Query("SELECT * FROM album")
    fun getAll(): LiveData<List<Album>>

    @Query("SELECT * FROM album WHERE artistId = :artistId ")
    fun getAlbum(artistId: Long): LiveData<List<Album>>

    @Update
    fun update(artist: Album): Int
}