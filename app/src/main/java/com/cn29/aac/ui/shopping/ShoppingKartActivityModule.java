package com.cn29.aac.ui.shopping;

import android.databinding.DataBindingUtil;
import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityShoppingKartBinding;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 20/10/2017.
 */

@Module
public class ShoppingKartActivityModule {

  @Provides
  ActivityShoppingKartBinding provideBinding(ShoppingKartActivity shoppingKartActivity) {
    return DataBindingUtil.setContentView(shoppingKartActivity, R.layout.activity_shopping_kart);
  }
}
