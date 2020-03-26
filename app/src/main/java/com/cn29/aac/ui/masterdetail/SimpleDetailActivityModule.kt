package com.cn29.aac.ui.masterdetail


import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Charles Ng on 3/10/2017.
 */
@Module
class SimpleDetailActivityModule {
    @Provides
    fun provideVm(simpleDetailActivity: SimpleDetailActivity,
                  factory: SimpleMasterDetailShareViewModelFactory): SimpleMasterDetailShareViewModel {
        return ViewModelProvider(simpleDetailActivity, factory)
                .get(
                        SimpleMasterDetailShareViewModel::class.java)
    }
}