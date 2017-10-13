package com.cn29.aac.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.ui.main.vm.AppArchNavViewModel;
import com.cn29.aac.ui.main.vm.AppArchNavViewModelFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 13/10/2017.
 */

@Module
public class AppArchNavigationDrawerModule {

  @Provides
  AppArchNavViewModel provideVm(AppArchNavigationDrawer drawer,
      AppArchNavViewModelFactory factory) {
    return ViewModelProviders.of(drawer, factory).get(AppArchNavViewModel.class);
  }
}
