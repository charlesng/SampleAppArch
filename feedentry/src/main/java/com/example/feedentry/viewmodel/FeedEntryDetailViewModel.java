package com.example.feedentry.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.repository.bean.FeedEntryRepository;

/**
 * Created by Charles Ng on 11/9/2017.
 */

public class FeedEntryDetailViewModel extends ViewModel {

  private final FeedEntryRepository feedEntryDBRepository = new FeedEntryRepository();

  public void init(Context context) {
    feedEntryDBRepository.init(context);
  }

  public LiveData<FeedEntry> getFeedEntry(int uid) {
    return feedEntryDBRepository.findByUid(uid);
  }

  public boolean update(FeedEntry feedEntry){
    return feedEntryDBRepository.update(feedEntry) > 0;
  }
}
