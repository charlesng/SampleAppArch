package com.cn29.aac.ui.main.vm

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

/**
 * Created by Charles Ng on 13/10/2017.
 */
class AppArchNavViewModel(var sharedPreferences: SharedPreferences) :
        ViewModel() {
    fun logout() {
        sharedPreferences.edit().putBoolean("isLogin", false).apply()
    }

}