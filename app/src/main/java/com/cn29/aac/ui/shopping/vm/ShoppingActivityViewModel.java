package com.cn29.aac.ui.shopping.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by Charles Ng on 23/10/2017.
 */

public class ShoppingActivityViewModel extends ViewModel {

  private MutableLiveData<String> queryData;

  public ShoppingActivityViewModel() {
    this.queryData = new MutableLiveData<>();
  }

  public void query(String query) {
    queryData.setValue(query);
  }

  public LiveData<String> getQueryData() {
    return queryData;
  }
}
