package com.cn29.aac.ui.shoppingkart

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityShoppingKartBinding
import com.cn29.aac.ui.shoppingkart.vm.ShoppingKartActivityViewModel
import com.cn29.aac.ui.shoppingkart.vm.ShoppingKartActivityViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ShoppingKartActivityModule {
    @Provides
    fun provideBinding(activity: ShoppingKartActivity): ActivityShoppingKartBinding {
        return DataBindingUtil.setContentView(activity,
                                              R.layout.activity_shopping_kart)
    }

    @Provides
    fun provideVm(factory: ShoppingKartActivityViewModelFactory,
                  activity: ShoppingKartActivity): ShoppingKartActivityViewModel {
        return ViewModelProvider(activity, factory)
                .get(ShoppingKartActivityViewModel::class.java)
    }
}