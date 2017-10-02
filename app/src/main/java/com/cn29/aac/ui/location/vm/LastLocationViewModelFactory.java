package com.cn29.aac.ui.location.vm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.cn29.aac.repo.LastLocationListener;


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
