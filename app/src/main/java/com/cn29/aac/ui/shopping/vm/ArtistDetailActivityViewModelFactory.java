package com.cn29.aac.ui.shopping.vm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import javax.inject.Inject;

/**
 * Created by Charles Ng on 23/10/2017.
 */

public class ArtistDetailActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  @Inject
  public ArtistDetailActivityViewModelFactory() {
    super();
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) new ArtistDetailActivityViewModel();
  }
}
