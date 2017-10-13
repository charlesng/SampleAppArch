package com.cn29.aac.datasource.feedentry.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.cn29.aac.datasource.feedentry.db.migration.Migration1_2;
import com.cn29.aac.datasource.feedentry.db.migration.Migration2_3;
import com.cn29.aac.repo.feedentry.FeedEntry;

/**
 * Created by Charles Ng on 5/9/2017.
 */

@Database(entities = {FeedEntry.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
  private static AppDatabase appDatabase;

  public static AppDatabase getDb(Context context) {
    if(appDatabase == null) {
      appDatabase = Room.databaseBuilder(context, AppDatabase.class, "feedentry-db")
          .fallbackToDestructiveMigration()
          .addMigrations(Migration1_2.newInstance(), Migration2_3.newInstance())
          .build();
    }
    return appDatabase;
  }

  public abstract FeedEntryDAO feedEntryDao();
}
