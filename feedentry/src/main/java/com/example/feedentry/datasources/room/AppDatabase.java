package com.example.feedentry.datasources.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.feedentry.datasources.room.migration.Migration1_2;
import com.example.feedentry.datasources.room.migration.Migration2_3;
import com.example.feedentry.repository.bean.FeedEntry;

/**
 * Created by Charles Ng on 5/9/2017.
 */

@Database(entities = {FeedEntry.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
  private static AppDatabase appDatabase;
  public abstract FeedEntryDAO feedEntryDao();

  public static AppDatabase getDb(Context context) {
    if(appDatabase == null) {
      appDatabase = Room.databaseBuilder(context, AppDatabase.class, "feedentry-db")
          .fallbackToDestructiveMigration()
          .addMigrations(Migration1_2.newInstance(), Migration2_3.newInstance())
          .build();
    }
    return appDatabase;
  }
}
