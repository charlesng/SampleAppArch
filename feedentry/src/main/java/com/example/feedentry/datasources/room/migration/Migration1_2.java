package com.example.feedentry.datasources.room.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;

/**
 * Created by Charles Ng on 11/9/2017.
 */

public class Migration1_2 extends Migration {

  /**
   * Creates a new migration between {@code startVersion} and {@code endVersion}.
   *
   * @param startVersion The start version of the database.
   * @param endVersion The end version of the database after this migration is applied.
   */
  public Migration1_2(int startVersion, int endVersion) {
    super(startVersion, endVersion);
  }

  public static Migration1_2 newInstance() {
    return new Migration1_2(1, 2);
  }

  @Override
  public void migrate(SupportSQLiteDatabase database) {
    database.execSQL("ALTER TABLE feedEntrys ADD COLUMN imageUrl TEXT ");
  }
}
