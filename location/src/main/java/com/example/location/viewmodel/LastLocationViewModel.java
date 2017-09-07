package com.example.location.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.location.Location;
import com.example.location.repository.LastLocationListener;

/**
 * Created by Charles Ng on 7/9/2017.
 */

public class LastLocationViewModel extends ViewModel {

  private MutableLiveData<Location> lastKnowLocation;
  private LastLocationListener lastLocationListener;

  public void init(Context context) {
    if (lastKnowLocation == null) {
      lastKnowLocation = new MutableLiveData<>();
    }
    if (lastLocationListener == null) {
      lastLocationListener = new LastLocationListener(context);
    }
  }

  public LiveData<Location> getLastKnowLocation() {
    return lastLocationListener;

  }
}
