package com.cn29.aac.ui.viewpager

import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel
import dagger.Module
import dagger.Provides

@Module
class BlankFragmentBModule {
    @Provides
    fun providePagerAgentVm(blankFragmentB: BlankFragmentB): PagerAgentViewModel {
        return ViewModelProvider(blankFragmentB.requireActivity())
                .get(PagerAgentViewModel::class.java)
    }
}