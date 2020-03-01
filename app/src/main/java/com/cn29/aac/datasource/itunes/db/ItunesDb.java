package com.cn29.aac.datasource.itunes.db;

import com.cn29.aac.repo.itunes.Album;
import com.cn29.aac.repo.itunes.Artist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Charles Ng on 19/10/2017.
 */
@Database(entities = {Artist.class, Album.class}, version = 3)
public abstract class ItunesDb extends RoomDatabase {

  public abstract ArtistDao artistDao();

  public abstract AlbumDao albumDao();
}
