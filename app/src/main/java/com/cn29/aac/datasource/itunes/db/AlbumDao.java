package com.cn29.aac.datasource.itunes.db;

import com.cn29.aac.repo.itunes.Album;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by Charles Ng on 20/10/2017.
 */

@Dao
public abstract class AlbumDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(Album... artists);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(List<Album> artists);

  @Query("SELECT * FROM album")
  public abstract LiveData<List<Album>> getAll();

  @Query("SELECT * FROM album WHERE artistId = :artistId ")
  public abstract LiveData<List<Album>> get(int artistId);

  @Update
  public abstract int update(Album artist);
}
