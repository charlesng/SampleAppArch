package com.cn29.aac.ui.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by Charles Ng on 12/10/2017.
 */
class FragmentPermissionComponent(private val fragment: Fragment,
                                  private val permissions: Array<String>,
                                  private val requestCode: Int) :
        LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        fragment.requestPermissions(permissions, requestCode)
    }

    interface PermissionCallback {
        fun onRequestPermissionsResult(requestCode: Int,
                                       permissions: Array<String>,
                                       grantResults: IntArray?)
    }

    init {
        fragment.lifecycle.addObserver(this)
    }
}