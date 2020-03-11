package com.cn29.aac.datasource.itunes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cn29.aac.repo.itunes.Album
import com.cn29.aac.repo.itunes.Artist

@Database(entities = [Artist::class, Album::class], version = 3)
abstract class ItunesDb : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
    abstract fun albumDao(): AlbumDao
}