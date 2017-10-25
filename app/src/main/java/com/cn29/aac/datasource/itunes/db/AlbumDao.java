package com.cn29.aac.datasource.itunes.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.cn29.aac.repo.itunes.Album;
import java.util.List;

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
