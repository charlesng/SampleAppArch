package com.example.location.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.example.location.repository.LastLocationListener;

/**
 * Created by Charles Ng on 20/9/2017.
 */

public class LastLocationViewModelFactory extends ViewModelProvider.NewInstanceFactory{
  private LastLocationListener lastLocationListener;
  public LastLocationViewModelFactory(LastLocationListener lastLocationListener) {
    this.lastLocationListener = lastLocationListener;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new LastLocationViewModel(lastLocationListener);
  }
}
