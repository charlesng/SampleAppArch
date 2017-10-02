package com.cn29.aac.ui.feedentry.vm;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.cn29.aac.repo.bean.FeedEntryRepository;


/**
 * Created by Charles Ng on 20/9/2017.
 */

public class FeedEntryListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
  private FeedEntryRepository feedEntryRepository;
  private LifecycleOwner lifecycleOwner;


  public FeedEntryListViewModelFactory( FeedEntryRepository feedEntryRepository) {
    this.feedEntryRepository = feedEntryRepository;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new FeedEntryListViewModel(feedEntryRepository);
  }
}
