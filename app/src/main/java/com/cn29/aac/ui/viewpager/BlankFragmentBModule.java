package com.cn29.aac.ui.viewpager;

import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel;

import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 3/10/2017.
 */

@Module
public class BlankFragmentBModule {

  @Provides
  PagerAgentViewModel providePagerAgentVm(BlankFragmentB blankFragmentB) {
    return ViewModelProviders.of(blankFragmentB.getActivity()).get(PagerAgentViewModel.class);
  }

}
