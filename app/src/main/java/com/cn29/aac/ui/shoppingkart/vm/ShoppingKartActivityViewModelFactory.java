package com.cn29.aac.ui.shoppingkart.vm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import javax.inject.Inject;

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
