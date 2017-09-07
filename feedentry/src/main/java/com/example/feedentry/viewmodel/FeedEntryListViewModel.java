package com.example.feedentry.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.repository.bean.FeedEntryRepository;
import java.security.KeyStore.Entry;
import java.util.List;

/**
 * Created by Charles Ng on 6/9/2017.
 */

public class FeedEntryListViewModel extends ViewModel {
  private MutableLiveData<List<FeedEntry>> feedEntries = new MutableLiveData<>();
  private final FeedEntryRepository feedEntryDBRepository = new FeedEntryRepository();

  public void init(Context context) {
    feedEntryDBRepository.init(context);
  }

  public LiveData<List<FeedEntry>> getFeedEntrys() {
    return feedEntryDBRepository.getAllFeedEntry();
  }

  public LiveData<List<FeedEntry>> insert(FeedEntry... feedEntries) {
    feedEntryDBRepository.insert(feedEntries);
    return feedEntryDBRepository.getAllFeedEntry();
  }


}
