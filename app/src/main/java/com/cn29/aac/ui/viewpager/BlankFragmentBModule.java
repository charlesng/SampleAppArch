package com.cn29.aac.ui.viewpager;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel;
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
