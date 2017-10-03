package com.cn29.aac.ui.masterdetail;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 3/10/2017.
 */

@Module
public class SimpleDetailFragmentModule {

  @Provides
  SimpleMasterDetailShareViewModel provideVm(SimpleDetailFragment simpleDetailFragment) {
    return ViewModelProviders.of(simpleDetailFragment.getActivity())
        .get(SimpleMasterDetailShareViewModel.class);

  }

}
