package com.example.feedentry.repository.bean;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.feedentry.datasources.room.AppDatabase;
import com.example.feedentry.datasources.room.FeedEntryDAO;
import java.util.List;

/**
 * Created by Charles Ng on 6/9/2017.
 */

public class FeedEntryRepository extends LiveData<List<FeedEntry>> {
  private FeedEntryDAO feedEntryDAO;
  public void init(Context context) {
    AppDatabase db = Room.databaseBuilder(context,
        AppDatabase.class, "feedentry-db").build();
    feedEntryDAO = db.feedEntryDao();
  }

  public LiveData<List<FeedEntry>>  getAllFeedEntry() {
    return feedEntryDAO.getAll();
  }

  public void insert(FeedEntry... feedEntries)
  {
    feedEntryDAO.insertAll();
  }

}
