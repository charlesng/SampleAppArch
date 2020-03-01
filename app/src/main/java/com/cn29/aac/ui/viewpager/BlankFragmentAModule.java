package com.cn29.aac.ui.viewpager;

import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel;

import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 3/10/2017.
 */

@Module
public class BlankFragmentAModule {

  @Provides
  PagerAgentViewModel providePagerAgentVm(BlankFragmentA blankFragmentA) {
    return ViewModelProviders.of(blankFragmentA.getActivity()).get(PagerAgentViewModel.class);
  }
}
