package com.cn29.aac.ui.feedentrydetail.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.cn29.aac.repo.feedentry.FeedEntryRepository

/**
 * Created by Charles Ng on 20/9/2017.
 */
class FeedEntryDetailViewModelFactory(private val feedEntryRepository: FeedEntryRepository,
                                      uid: Int) :
        NewInstanceFactory() {
    private var uid = 0
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedEntryDetailViewModel(feedEntryRepository, uid) as T
    }

    init {
        this.uid = uid
    }
}