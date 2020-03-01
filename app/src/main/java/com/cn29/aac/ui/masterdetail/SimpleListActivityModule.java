package com.cn29.aac.ui.masterdetail;

import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel;
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModelFactory;

import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 3/10/2017.
 */

@Module
public class SimpleListActivityModule {

  @Provides
  SimpleMasterDetailShareViewModel provideVm(SimpleListActivity simpleListActivity,
      SimpleMasterDetailShareViewModelFactory factory) {
    return ViewModelProviders
        .of(simpleListActivity, factory).get(SimpleMasterDetailShareViewModel.class);

  }


}
