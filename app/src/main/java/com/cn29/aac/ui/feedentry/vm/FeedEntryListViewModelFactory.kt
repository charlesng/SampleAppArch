package com.cn29.aac.ui.feedentry.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.cn29.aac.repo.feedentry.FeedEntryRepository
import javax.inject.Inject

/**
 * Created by Charles Ng on 20/9/2017.
 */
class FeedEntryListViewModelFactory @Inject constructor(private val feedEntryRepository: FeedEntryRepository) :
        NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedEntryListViewModel(feedEntryRepository) as T
    }

}