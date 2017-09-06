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

public class FeedEntryDBRepository extends LiveData<List<FeedEntry>> {


  private FeedEntryDAO feedEntryDAO;

  public void init(Context context) {
    AppDatabase db = Room.databaseBuilder(context,
        AppDatabase.class, "feedentry-db").build();
    feedEntryDAO = db.feedEntryDao();
  }

  public FeedEntryDAO getFeedEntryDAO() {
    return feedEntryDAO;
  }

}
