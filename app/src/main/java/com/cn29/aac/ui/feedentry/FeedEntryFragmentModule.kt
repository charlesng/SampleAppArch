package com.cn29.aac.ui.feedentry

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.FragmentFeedentryListBinding
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedEntryFragmentModule {
    @Provides
    fun provideViewModel(feedEntryFragment: FeedEntryFragment,
                         factory: FeedEntryListViewModelFactory): FeedEntryListViewModel {
        return ViewModelProvider(feedEntryFragment.requireActivity(), factory)
                .get(FeedEntryListViewModel::class.java)
    }

    @Provides
    fun provideDataBinding(feedEntryFragment: FeedEntryFragment): FragmentFeedentryListBinding {
        return DataBindingUtil
                .inflate(feedEntryFragment.activity!!.layoutInflater,
                         R.layout.fragment_feedentry_list, null,
                         false)
    }
}