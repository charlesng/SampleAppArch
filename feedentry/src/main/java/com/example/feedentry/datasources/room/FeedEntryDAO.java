package com.example.feedentry.datasources.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.feedentry.repository.bean.FeedEntry;
import java.util.List;

/**
 * Created by Charles Ng on 5/9/2017.
 */

@Dao
public interface FeedEntryDAO {

  @Query("SELECT * FROM feedEntrys")
  List<FeedEntry> getAll();

  @Query("SELECT * FROM feedEntrys WHERE uid IN (:userIds)")
  List<FeedEntry> loadAllByIds(int[] userIds);

  @Query("SELECT * FROM feedEntrys WHERE title LIKE :title AND "
      + "subTitle LIKE :subTitle LIMIT 1")
  FeedEntry findByName(String title, String subTitle);

  @Insert
  void insertAll(FeedEntry... feedEntries);

  @Delete
  void delete(FeedEntry feedEntry);
}
