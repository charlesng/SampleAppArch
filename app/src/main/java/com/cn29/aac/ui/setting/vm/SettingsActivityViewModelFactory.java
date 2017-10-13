package com.cn29.aac.ui.setting.vm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import javax.inject.Inject;

/**
 * Created by Charles Ng on 13/10/2017.
 */

public class SettingsActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  @Inject
  public SettingsActivityViewModelFactory() {
    super();
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new SettingsActivityViewModel();
  }
}
