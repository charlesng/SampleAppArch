package com.cn29.aac.ui.feedentrydetail.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cn29.aac.repo.feedentry.FeedEntry
import com.cn29.aac.repo.feedentry.FeedEntryRepository

/**
 * Created by Charles Ng on 11/9/2017.
 */
class FeedEntryDetailViewModel(private val feedEntryDBRepository: FeedEntryRepository,
                               uid: Int) :
        ViewModel() {
    private var feedEntry: LiveData<FeedEntry?>?
    fun getFeedEntry(uid: Int): LiveData<FeedEntry?>? {
        feedEntry = feedEntryDBRepository.findByUid(uid)
        return feedEntry
    }

    fun update(feedEntry: FeedEntry?): Int {
        return feedEntryDBRepository.update(feedEntry)!!
    }

    init {
        feedEntry = feedEntryDBRepository.findByUid(uid)
    }
}