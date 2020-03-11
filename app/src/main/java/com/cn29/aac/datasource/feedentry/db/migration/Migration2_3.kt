package com.cn29.aac.datasource.feedentry.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration2_3(startVersion: Int,
                   endVersion: Int) : Migration(startVersion, endVersion) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE feedEntrys ADD COLUMN favourite INTEGER NOT NULL DEFAULT 0 ")
    }

    companion object {
        @JvmStatic
        fun newInstance(): Migration2_3 {
            return Migration2_3(2, 3)
        }
    }
}