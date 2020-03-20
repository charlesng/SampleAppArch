package com.cn29.aac.ui.feedentry

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.DialogFeedentryBinding
import com.cn29.aac.repo.feedentry.FeedEntry
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedActivityModule {
    @Provides
    fun provideViewModel(feedEntry: FeedEntry,
                         feedActivity: FeedActivity,
                         factory: FeedEntryListViewModelFactory): FeedEntryListViewModel {
        val viewModel = ViewModelProvider(feedActivity, factory).get(
                FeedEntryListViewModel::class.java)
        viewModel.feedEntry.set(feedEntry)
        return viewModel
    }

    @Provides
    fun provideDialogBinding(activity: FeedActivity,
                             feedEntryListViewModel: FeedEntryListViewModel): DialogFeedentryBinding {
        val binding: DialogFeedentryBinding = DataBindingUtil
                .inflate(LayoutInflater.from(activity),
                         R.layout.dialog_feedentry,
                         null,
                         false)
        binding.feedEntry = feedEntryListViewModel.feedEntry.get()
        return binding
    }

    @Provides
    fun provideDefaultFeedEntry(): FeedEntry {
        return FeedEntry("abc", "", false, null, 0)
    }
}