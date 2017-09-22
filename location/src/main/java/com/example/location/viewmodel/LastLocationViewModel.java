package com.example.location.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;
import com.example.location.repository.LastLocationListener;

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
