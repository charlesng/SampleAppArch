package com.cn29.aac.ui.common

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by Charles Ng on 13/10/2017.
 */
class ActivityPermissionComponent(activity: AppCompatActivity,
                                  permissions: Array<String>,
                                  requestCode: Int) :
        LifecycleObserver {
    private val activity: Activity
    private val permissions: Array<String>
    private val requestCode: Int

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    init {
        this.activity = activity
        this.permissions = permissions
        this.requestCode = requestCode
    }
}