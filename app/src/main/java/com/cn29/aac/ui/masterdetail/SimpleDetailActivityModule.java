package com.cn29.aac.ui.masterdetail;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel;
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModelFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 3/10/2017.
 */

@Module
public class SimpleDetailActivityModule {


  @Provides
  SimpleMasterDetailShareViewModel provideVm(SimpleDetailActivity simpleDetailActivity,
      SimpleMasterDetailShareViewModelFactory factory) {
    return ViewModelProviders
        .of(simpleDetailActivity, factory).get(SimpleMasterDetailShareViewModel.class);
  }
}
