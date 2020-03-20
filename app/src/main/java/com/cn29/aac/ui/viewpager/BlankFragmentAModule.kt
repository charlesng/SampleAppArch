package com.cn29.aac.ui.viewpager

import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel
import dagger.Module
import dagger.Provides


@Module
class BlankFragmentAModule {
    @Provides
    fun providePagerAgentVm(blankFragmentA: BlankFragmentA): PagerAgentViewModel {
        return ViewModelProvider(blankFragmentA.requireActivity())
                .get(PagerAgentViewModel::class.java)
    }
}