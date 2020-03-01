package com.cn29.aac.ui.feedentry.vm;

import com.cn29.aac.repo.feedentry.FeedEntryRepository;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


/**
 * Created by Charles Ng on 20/9/2017.
 */

public class FeedEntryListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
  private FeedEntryRepository feedEntryRepository;

  @Inject
  public FeedEntryListViewModelFactory( FeedEntryRepository feedEntryRepository) {
    this.feedEntryRepository = feedEntryRepository;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new FeedEntryListViewModel(feedEntryRepository);
  }
}
