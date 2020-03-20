package com.cn29.aac.ui.location

import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.ui.location.vm.LastLocationViewModel
import com.cn29.aac.ui.location.vm.LastLocationViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by charlesng0209 on 2/10/2017.
 */
@Module
class LocationActivityModule {
    @Provides
    fun provideLastLocationVM(factory: LastLocationViewModelFactory,
                              activity: LocationActivity): LastLocationViewModel {
        return ViewModelProvider(activity, factory)
                .get(
                        LastLocationViewModel::class.java)
    }
}