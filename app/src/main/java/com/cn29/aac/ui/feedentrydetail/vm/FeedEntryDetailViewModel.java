package com.cn29.aac.ui.feedentrydetail.vm;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.repo.feedentry.FeedEntryRepository;

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
