package com.cn29.aac.ui.feedentrydetail.vm;

import com.cn29.aac.repo.feedentry.FeedEntryRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


/**
 * Created by Charles Ng on 20/9/2017.
 */

public class FeedEntryDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
  private FeedEntryRepository feedEntryRepository;
  private int uid = 0;
  public FeedEntryDetailViewModelFactory(FeedEntryRepository feedEntryRepository,int uid) {
      this.feedEntryRepository = feedEntryRepository;
      this.uid = uid;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new FeedEntryDetailViewModel(feedEntryRepository,uid);
  }
}
