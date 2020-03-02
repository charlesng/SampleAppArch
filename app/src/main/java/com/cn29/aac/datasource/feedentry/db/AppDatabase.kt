package com.cn29.aac.datasource.feedentry.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cn29.aac.datasource.feedentry.db.migration.Migration1_2
import com.cn29.aac.datasource.feedentry.db.migration.Migration2_3
import com.cn29.aac.repo.feedentry.FeedEntry

@Database(entities = [FeedEntry::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedEntryDao(): FeedEntryDAO?

    companion object {
        private var appDatabase: AppDatabase? = null
        @JvmStatic
        fun getDb(context: Context?): AppDatabase? {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context!!, AppDatabase::class.java, "feedentry-db")
                        .fallbackToDestructiveMigration()
                        .addMigrations(Migration1_2.newInstance(), Migration2_3.newInstance())
                        .build()
            }
            return appDatabase
        }
    }
}