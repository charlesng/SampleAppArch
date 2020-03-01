package com.cn29.aac.datasource.itunes.db;

import com.cn29.aac.repo.itunes.Artist;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by Charles Ng on 19/10/2017.
 */

@Dao
public abstract class ArtistDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(Artist... artists);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(List<Artist> artists);

  @Query("SELECT * FROM artist WHERE artistName like '%' || :query  || '%'")
  public abstract LiveData<List<Artist>> getArtists(String query);


  @Update
  public abstract int update(Artist artist);
}
