package com.cn29.aac.datasource.feedentry.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cn29.aac.repo.feedentry.FeedEntry

@Dao
interface FeedEntryDAO {
    @Query("SELECT * FROM feedEntrys")
    fun getAll(): LiveData<List<FeedEntry>>

    @Query("SELECT * FROM feedEntrys WHERE uid = :uid LIMIT 1")
    fun findByUid(uid: Int): LiveData<FeedEntry>

    @Insert
    fun insertAll(vararg feedEntries: FeedEntry?): List<Long>

    @Delete
    fun delete(feedEntry: FeedEntry?): Int?

    @Update
    fun update(feedEntry: FeedEntry?): Int?
}