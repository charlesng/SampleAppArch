package com.example.feedentry.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.example.feedentry.repository.bean.FeedEntryRepository;
import javax.inject.Inject;

/**
 * Created by Charles Ng on 20/9/2017.
 */

public class FeedEntryListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
  private FeedEntryRepository feedEntryRepository;
  @Inject
  public FeedEntryListViewModelFactory(FeedEntryRepository feedEntryRepository) {
    this.feedEntryRepository = feedEntryRepository;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new FeedEntryListViewModel(feedEntryRepository);
  }
}
