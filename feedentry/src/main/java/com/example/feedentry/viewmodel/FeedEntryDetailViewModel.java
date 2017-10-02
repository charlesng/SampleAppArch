package com.example.feedentry.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.repository.bean.FeedEntryRepository;

/**
 * Created by Charles Ng on 11/9/2017.
 */

public  class FeedEntryDetailViewModel extends ViewModel {

  private FeedEntryRepository feedEntryDBRepository = new FeedEntryRepository();
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
