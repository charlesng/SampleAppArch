package com.cn29.aac.ui.shopping;

import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityShoppingBinding;
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel;
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 20/10/2017.
 */

@Module
public class ShoppingActivityModule {

  @Provides
  ActivityShoppingBinding provideBinding(ShoppingActivity shoppingActivity) {
    return DataBindingUtil.setContentView(shoppingActivity, R.layout.activity_shopping);
  }

  @Provides
  ShoppingActivityViewModel provideVM(ShoppingActivityViewModelFactory factory,
      ShoppingActivity shoppingActivity) {
    return ViewModelProviders.of(shoppingActivity, factory)
        .get(ShoppingActivityViewModel.class);
  }
}
