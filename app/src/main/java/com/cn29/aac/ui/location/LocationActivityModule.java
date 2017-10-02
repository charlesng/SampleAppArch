package com.cn29.aac.ui.location;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.ui.location.vm.LastLocationViewModel;
import com.cn29.aac.ui.location.vm.LastLocationViewModelFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by charlesng0209 on 2/10/2017.
 */

@Module
public class LocationActivityModule {

  @Provides
  LastLocationViewModel provideLastLocationVM(LastLocationViewModelFactory factory,
      LocationActivity activity) {
    return ViewModelProviders.of(activity, factory).get(LastLocationViewModel.class);
  }
}
