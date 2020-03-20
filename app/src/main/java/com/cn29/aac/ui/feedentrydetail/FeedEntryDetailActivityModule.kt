package com.cn29.aac.ui.feedentrydetail

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityFeedEntryDetailBinding
import com.cn29.aac.databinding.DialogFeedentryBinding
import com.cn29.aac.repo.feedentry.FeedEntry
import com.cn29.aac.repo.feedentry.FeedEntryRepository
import com.cn29.aac.ui.feedentrydetail.vm.FeedEntryDetailViewModel
import com.cn29.aac.ui.feedentrydetail.vm.FeedEntryDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedEntryDetailActivityModule {
    @Provides
    fun getUid(activity: FeedEntryDetailActivity): Int {
        return activity.intent
                .getIntExtra(FeedEntryDetailActivity.EXTRA_POSITION, 1)
    }

    @Provides
    fun provideFeedEntryDetailViewModel(uid: Int,
                                        feedEntryRepository: FeedEntryRepository?): FeedEntryDetailViewModelFactory {
        /*
    Use Code lab injection reference example
    https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html?index=..%2F..%2Findex#10
     */
        return FeedEntryDetailViewModelFactory(feedEntryRepository!!, uid)
    }

    @Provides
    fun provideViewModel(feedActivity: FeedEntryDetailActivity,
                         factory: FeedEntryDetailViewModelFactory): FeedEntryDetailViewModel {
        return ViewModelProvider(feedActivity, factory)
                .get(FeedEntryDetailViewModel::class.java)
    }

    @Provides
    fun provideDefaultFeedEntry(): FeedEntry {
        return FeedEntry("", "", false, null, 0)
    }

    @Provides
    fun provideBinding(activity: FeedEntryDetailActivity?): ActivityFeedEntryDetailBinding {
        return DataBindingUtil.setContentView(activity!!,
                                              R.layout.activity_feed_entry_detail)
    }

    @Provides
    fun provideDialogBinding(activity: FeedEntryDetailActivity?): DialogFeedentryBinding {
        return DataBindingUtil
                .inflate(LayoutInflater.from(activity),
                         R.layout.dialog_feedentry,
                         null,
                         false)
    }
}