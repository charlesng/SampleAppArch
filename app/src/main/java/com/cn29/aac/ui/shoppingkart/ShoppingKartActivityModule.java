package com.cn29.aac.ui.shoppingkart;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityShoppingKartBinding;
import com.cn29.aac.ui.shoppingkart.vm.ShoppingKartActivityViewModel;
import com.cn29.aac.ui.shoppingkart.vm.ShoppingKartActivityViewModelFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 25/10/2017.
 */

@Module
public class ShoppingKartActivityModule {

  @Provides
  ActivityShoppingKartBinding provideBinding(ShoppingKartActivity activity) {
    return DataBindingUtil.setContentView(activity, R.layout.activity_shopping_kart);
  }

  @Provides
  ShoppingKartActivityViewModel provideVm(ShoppingKartActivityViewModelFactory factory,
      ShoppingKartActivity activity) {
    return ViewModelProviders.of(activity, factory).get(ShoppingKartActivityViewModel.class);
  }
}
