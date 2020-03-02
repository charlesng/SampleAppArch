package com.cn29.aac.datasource.feedentry.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1_2
/**
 * Creates a new migration between `startVersion` and `endVersion`.
 *
 * @param startVersion The start version of the database.
 * @param endVersion The end version of the database after this migration is applied.
 */(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE feedEntrys ADD COLUMN imageUrl TEXT ")
    }

    companion object {
        @JvmStatic
        fun newInstance(): Migration1_2 {
            return Migration1_2(1, 2)
        }
    }
}