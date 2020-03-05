package com.cn29.aac.ui.shoppingkart.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import javax.inject.Inject

/**
 * Created by Charles Ng on 25/10/2017.
 */
class ShoppingKartActivityViewModelFactory @Inject constructor() :
        NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingKartActivityViewModel() as T
    }
}