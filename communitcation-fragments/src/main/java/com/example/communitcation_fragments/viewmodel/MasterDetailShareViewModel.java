package com.example.communitcation_fragments.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by charlesng0209 on 25/6/2017.
 */

public class MasterDetailShareViewModel extends ViewModel {

  private MutableLiveData<String> selectFeedEntryName;

  public void init() {
    selectFeedEntryName = new MutableLiveData<>();
  }

  public void selectFeedEntry(String feedEntry) {
    selectFeedEntryName.setValue(feedEntry);
  }

  public LiveData<String> getSelectFeedEntry() {
    return this.selectFeedEntryName;
  }
}
