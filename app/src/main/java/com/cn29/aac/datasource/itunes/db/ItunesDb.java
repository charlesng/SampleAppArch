package com.cn29.aac.datasource.itunes.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.cn29.aac.repo.itunes.Album;
import com.cn29.aac.repo.itunes.Artist;

/**
 * Created by Charles Ng on 19/10/2017.
 */
@Database(entities = {Artist.class, Album.class}, version = 3)
public abstract class ItunesDb extends RoomDatabase {

  public abstract ArtistDao artistDao();

  public abstract AlbumDao albumDao();
}
