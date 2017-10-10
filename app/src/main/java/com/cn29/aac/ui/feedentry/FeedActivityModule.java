package com.cn29.aac.ui.feedentry;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModelFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;


@Module
@Singleton
public class FeedActivityModule {

  /**
   * Created by Charles Ng on 26/9/2017.
   */


  @Provides
  FeedEntryListViewModel provideViewModel(FeedActivity feedActivity,
      FeedEntryListViewModelFactory factory) {
    return ViewModelProviders.of(feedActivity, factory)
        .get(FeedEntryListViewModel.class);
  }


}