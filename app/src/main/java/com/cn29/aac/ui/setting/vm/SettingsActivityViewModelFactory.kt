package com.cn29.aac.ui.setting.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import javax.inject.Inject

/**
 * Created by Charles Ng on 13/10/2017.
 */
class SettingsActivityViewModelFactory @Inject constructor() :
        NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingsActivityViewModel() as T
    }
}