package com.cn29.aac.ui.common

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by Charles Ng on 12/10/2017.
 */
class AlertDialogComponent(private val context: Context,
                           lifecycle: Lifecycle) :
        LifecycleObserver {
    private var alertDialog: AlertDialog? = null
    fun showDialog(alertDialog: AlertDialog) {
        this.alertDialog = alertDialog
        alertDialog.show()
    }

    fun hideDialog() {
        if (alertDialog != null) {
            alertDialog!!.dismiss()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        if (alertDialog != null) {
            alertDialog!!.dismiss()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        if (alertDialog != null) {
            alertDialog!!.dismiss()
        }
    }

    init {
        lifecycle.addObserver(this)
    }
}