package com.cn29.aac.ui.feedentrydetail.vm;


import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.repo.feedentry.FeedEntryRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Charles Ng on 11/9/2017.
 */

public  class FeedEntryDetailViewModel extends ViewModel {

  private FeedEntryRepository feedEntryDBRepository;
  private LiveData<FeedEntry> feedEntry;
  private int uid;

  public FeedEntryDetailViewModel(FeedEntryRepository feedEntryRepository, int uid) {
    this.feedEntryDBRepository = feedEntryRepository;
    this.uid = uid;
    this.feedEntry = feedEntryDBRepository.findByUid(uid);

  }


  public LiveData<FeedEntry> getFeedEntry(int uid) {
    this.feedEntry = feedEntryDBRepository.findByUid(uid);
    return feedEntry;
  }

  public int update(FeedEntry feedEntry) {
  return feedEntryDBRepository.update(feedEntry);

  }


}
