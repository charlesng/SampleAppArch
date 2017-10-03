package com.cn29.aac.ui.viewpager;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel;
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
