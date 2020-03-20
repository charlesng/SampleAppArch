package com.cn29.aac.ui.shopping

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityShoppingBinding
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class ShoppingActivityModule {
    @Provides
    fun provideBinding(shoppingActivity: ShoppingActivity): ActivityShoppingBinding {
        return DataBindingUtil.setContentView(shoppingActivity,
                                              R.layout.activity_shopping)
    }

    @Provides
    fun provideVM(factory: ShoppingActivityViewModelFactory,
                  shoppingActivity: ShoppingActivity): ShoppingActivityViewModel {
        return ViewModelProvider(shoppingActivity, factory)
                .get(ShoppingActivityViewModel::class.java)
    }
}