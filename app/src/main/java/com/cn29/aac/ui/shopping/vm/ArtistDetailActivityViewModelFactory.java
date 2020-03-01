package com.cn29.aac.ui.shopping.vm;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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
