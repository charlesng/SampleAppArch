package com.cn29.aac.ui.main;

import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityAppArchNavigationDrawerBinding;
import com.cn29.aac.ui.main.vm.AppArchNavViewModel;
import com.cn29.aac.ui.main.vm.AppArchNavViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
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

  @Provides
  ActivityAppArchNavigationDrawerBinding provideBinding(AppArchNavigationDrawer drawer) {
    return DataBindingUtil.setContentView(drawer, R.layout.activity_app_arch_navigation_drawer);
  }
}
