package com.cn29.aac.ui.location.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;
import com.cn29.aac.repo.location.LastLocationListener;


/**
 * Created by Charles Ng on 7/9/2017.
 */

public class LastLocationViewModel extends ViewModel {

  private LastLocationListener lastLocationListener;

  public LastLocationViewModel(LastLocationListener lastLocationListener) {
    this.lastLocationListener = lastLocationListener;

  }

  public LiveData<Location> getLastKnowLocation() {
    return lastLocationListener;

  }
}
