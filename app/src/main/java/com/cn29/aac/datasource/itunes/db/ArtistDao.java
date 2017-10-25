package com.cn29.aac.datasource.itunes.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.cn29.aac.repo.itunes.Artist;
import java.util.List;

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
