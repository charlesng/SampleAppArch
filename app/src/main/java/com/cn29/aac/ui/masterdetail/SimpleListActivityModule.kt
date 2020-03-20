package com.cn29.aac.ui.masterdetail

import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SimpleListActivityModule {
    @Provides
    fun provideVm(simpleListActivity: SimpleListActivity,
                  factory: SimpleMasterDetailShareViewModelFactory): SimpleMasterDetailShareViewModel {
        return ViewModelProvider(simpleListActivity, factory)
                .get(
                        SimpleMasterDetailShareViewModel::class.java)
    }
}