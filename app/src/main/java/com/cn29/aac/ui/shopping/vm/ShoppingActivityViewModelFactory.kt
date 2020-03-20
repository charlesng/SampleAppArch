package com.cn29.aac.ui.shopping.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import javax.inject.Inject

/**
 * Created by Charles Ng on 23/10/2017.
 */
class ShoppingActivityViewModelFactory @Inject constructor() :
        NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingActivityViewModel() as T
    }
}