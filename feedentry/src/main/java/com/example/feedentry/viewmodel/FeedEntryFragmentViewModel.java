package com.example.feedentry.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.repository.bean.FeedEntryDBRepository;
import java.util.List;

/**
 * Created by Charles Ng on 6/9/2017.
 */

public class FeedEntryFragmentViewModel extends ViewModel {

  private final FeedEntryDBRepository feedEntryDBRepository = new FeedEntryDBRepository();

  public void init(Context context) {
    feedEntryDBRepository.init(context);
  }

  public LiveData<List<FeedEntry>> getFeedEntrys() {
    return feedEntryDBRepository.getFeedEntryDAO().getAll();
  }
}
