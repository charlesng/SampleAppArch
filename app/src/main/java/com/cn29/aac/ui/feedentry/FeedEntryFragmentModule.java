package com.cn29.aac.ui.feedentry;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.repo.bean.FeedEntryRepository;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModelFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class FeedEntryFragmentModule {

  @Provides
  FeedEntryListViewModel provideViewModel(FeedEntryFragment feedEntryFragment,
      FeedEntryRepository feedEntryRepository) {
    FeedEntryListViewModelFactory factory = new FeedEntryListViewModelFactory(
        feedEntryRepository);
    return ViewModelProviders.of(feedEntryFragment.getActivity(), factory)
        .get(FeedEntryListViewModel.class);
  }


}