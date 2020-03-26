package com.cn29.aac.ui.main

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityAppArchNavigationDrawerBinding
import com.cn29.aac.ui.main.vm.AppArchNavViewModel
import com.cn29.aac.ui.main.vm.AppArchNavViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Charles Ng on 13/10/2017.
 */
@Module
class AppArchNavigationDrawerModule {
    @Provides
    fun provideVm(drawer: AppArchNavigationDrawer,
                  factory: AppArchNavViewModelFactory): AppArchNavViewModel {
        return ViewModelProvider(drawer, factory).get(
                AppArchNavViewModel::class.java)
    }

    @Provides
    fun provideBinding(drawer: AppArchNavigationDrawer?): ActivityAppArchNavigationDrawerBinding {
        return DataBindingUtil.setContentView(drawer!!,
                                              R.layout.activity_app_arch_navigation_drawer)
    }
}