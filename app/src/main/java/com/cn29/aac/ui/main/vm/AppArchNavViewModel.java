package com.cn29.aac.ui.main.vm;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

/**
 * Created by Charles Ng on 13/10/2017.
 */

public class AppArchNavViewModel extends ViewModel {

  SharedPreferences sharedPreferences;

  public AppArchNavViewModel(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  public void logout() {
    sharedPreferences.edit().putBoolean("isLogin", false).apply();
  }
}
