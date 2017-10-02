package com.cn29.aac.datasource.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.cn29.aac.repo.bean.FeedEntry;
import java.util.List;

/**
 * Created by Charles Ng on 5/9/2017.
 */

@Dao
public interface FeedEntryDAO {

  @Query("SELECT * FROM feedEntrys")
  LiveData<List<FeedEntry>> getAll();

  @Query("SELECT * FROM feedEntrys WHERE uid = :uid LIMIT 1")
  LiveData<FeedEntry> findByUid(int uid);

  @Insert
  void insertAll(FeedEntry... feedEntries);

  @Delete
  void delete(FeedEntry feedEntry);

  @Update
  int update(FeedEntry feedEntry);

}
