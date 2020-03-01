package com.cn29.aac.ui.shoppingkart.vm;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by Charles Ng on 25/10/2017.
 */

public class ShoppingKartActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  @Inject
  public ShoppingKartActivityViewModelFactory() {
    super();
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) new ShoppingKartActivityViewModel();
  }
}
