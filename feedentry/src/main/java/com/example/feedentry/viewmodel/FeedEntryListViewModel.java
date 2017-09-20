package com.example.feedentry.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.repository.bean.FeedEntryRepository;
import java.util.List;

/**
 * Created by Charles Ng on 6/9/2017.
 */

public class FeedEntryListViewModel extends ViewModel {

  private LiveData<List<FeedEntry>> feedEntries = new MutableLiveData<>();
  private FeedEntryRepository feedEntryDBRepository;

  public FeedEntryListViewModel(FeedEntryRepository feedEntryDBRepository) {
    this.feedEntryDBRepository = feedEntryDBRepository;
    this.feedEntries = feedEntryDBRepository.getAll();
  }


  public LiveData<List<FeedEntry>> getFeedEntrys() {
    return feedEntryDBRepository.getAll();
  }

  public LiveData<List<FeedEntry>> insert(FeedEntry... feedEntries) {
    feedEntryDBRepository.insertAll(feedEntries);
    return feedEntryDBRepository.getAll();
  }

  public void delete(FeedEntry feedEntry) {
    feedEntryDBRepository.delete(feedEntry);
  }


  public int update(FeedEntry feedEntry) {
    return feedEntryDBRepository.update(feedEntry);
  }

}
