package com.cn29.aac.ui.masterdetail.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by charlesng0209 on 25/6/2017.
 */

public class SimpleMasterDetailShareViewModel extends ViewModel {

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
