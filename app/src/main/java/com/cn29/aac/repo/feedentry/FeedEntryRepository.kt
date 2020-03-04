package com.cn29.aac.repo.feedentry

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.cn29.aac.datasource.feedentry.db.AppDatabase.Companion.getDb
import com.cn29.aac.datasource.feedentry.db.FeedEntryDAO
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Charles Ng on 6/9/2017.
 * Implements the FeedEntryDAO interface to handle all the situation
 */
@Singleton
class FeedEntryRepository @Inject constructor(application: Application?) :
        LiveData<List<FeedEntry?>?>(), FeedEntryDAO {
    private var feedEntryDAO: FeedEntryDAO? = null
    fun init(context: Context?) {
        val db = getDb(context)
        feedEntryDAO = db?.feedEntryDao()
    }

    override fun getAll(): LiveData<List<FeedEntry?>?>? {
        return feedEntryDAO?.getAll()
    }

    override fun findByUid(uid: Int): LiveData<FeedEntry?>? {
        return feedEntryDAO?.findByUid(uid)
    }

    override fun insertAll(vararg feedEntries: FeedEntry?) {
        feedEntryDAO?.insertAll(*feedEntries)
    }

    override fun delete(feedEntry: FeedEntry?) {
        feedEntryDAO?.delete(feedEntry)
    }

    override fun update(feedEntry: FeedEntry?): Int? {
        return feedEntryDAO?.update(feedEntry)
    }

    init {
        init(application)
    }
}