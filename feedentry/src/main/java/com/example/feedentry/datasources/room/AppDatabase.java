package com.example.feedentry.datasources.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.feedentry.repository.bean.FeedEntry;

/**
 * Created by Charles Ng on 5/9/2017.
 */

@Database(entities = {FeedEntry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  public abstract FeedEntryDAO feedEntryDao();

}
