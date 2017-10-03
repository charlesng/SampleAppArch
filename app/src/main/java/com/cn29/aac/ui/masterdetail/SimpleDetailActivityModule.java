package com.cn29.aac.ui.masterdetail;

import android.arch.lifecycle.ViewModelProviders;
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 3/10/2017.
 */

@Module
public class SimpleDetailActivityModule {

  @Provides
  SimpleMasterDetailShareViewModel provideVm(SimpleDetailActivity simpleDetailActivity) {
    SimpleMasterDetailShareViewModel masterDetailShareViewModel = ViewModelProviders
        .of(simpleDetailActivity).get(SimpleMasterDetailShareViewModel.class);
    masterDetailShareViewModel.init();
    masterDetailShareViewModel.selectFeedEntry(
        simpleDetailActivity.getIntent().getStringExtra(SimpleDetailFragment.ARG_ITEM_ID));
    return masterDetailShareViewModel;
  }
}
