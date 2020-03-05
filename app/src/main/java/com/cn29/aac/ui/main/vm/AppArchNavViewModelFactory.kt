package com.cn29.aac.ui.main.vm

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import javax.inject.Inject

/**
 * Created by Charles Ng on 13/10/2017.
 */
class AppArchNavViewModelFactory @Inject constructor(private val sharedPreferences: SharedPreferences) :
        NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppArchNavViewModel(sharedPreferences) as T
    }

}