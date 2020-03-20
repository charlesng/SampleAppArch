package com.cn29.aac.ui.viewpager

import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel
import dagger.Module
import dagger.Provides

@Module
class PagerActivityModule {
    @Provides
    fun providePagerAgentVm(pagerActivity: PagerActivity): PagerAgentViewModel {
        return ViewModelProvider(pagerActivity)
                .get(PagerAgentViewModel::class.java)
                .also {
                    it.init()
                }
    }
}