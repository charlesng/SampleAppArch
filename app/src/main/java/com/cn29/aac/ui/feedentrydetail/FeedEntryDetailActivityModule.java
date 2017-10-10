package com.cn29.aac.ui.feedentrydetail;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.repo.feedentry.FeedEntryRepository;
import com.cn29.aac.ui.feedentrydetail.vm.FeedEntryDetailViewModel;
import com.cn29.aac.ui.feedentrydetail.vm.FeedEntryDetailViewModelFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;


@Module
@Singleton
public class FeedEntryDetailActivityModule {


  @Provides
  int getUid(FeedEntryDetailActivity activity) {
    return activity.getIntent().getIntExtra(FeedEntryDetailActivity.EXTRA_POSITION, 1);
  }

  @Provides
  FeedEntryDetailViewModelFactory provideFeedEntryDetailViewModel(int uid,
      FeedEntryRepository feedEntryRepository) {
          /*
    Use Code lab injection reference example
    https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html?index=..%2F..%2Findex#10
     */
    return new FeedEntryDetailViewModelFactory(feedEntryRepository, uid);
  }

  @Provides
  FeedEntryDetailViewModel provideViewModel(FeedEntryDetailActivity feedActivity,
      FeedEntryDetailViewModelFactory factory) {
    return ViewModelProviders.of(feedActivity, factory)
        .get(FeedEntryDetailViewModel.class);
  }

  @Provides
  FeedEntry provideDefaultFeedEntry() {
    return new FeedEntry("", "");
  }

}