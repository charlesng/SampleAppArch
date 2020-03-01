package com.cn29.aac.ui.main.vm;

import android.content.SharedPreferences;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by Charles Ng on 13/10/2017.
 */

public class AppArchNavViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  private SharedPreferences sharedPreferences;

  @Inject
  public AppArchNavViewModelFactory(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new AppArchNavViewModel(sharedPreferences);
  }
}
