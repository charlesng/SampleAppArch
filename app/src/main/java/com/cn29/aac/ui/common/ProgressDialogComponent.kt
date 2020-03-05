package com.cn29.aac.ui.common

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.cn29.aac.R

/**
 * Created by Charles Ng on 11/10/2017.
 */
class ProgressDialogComponent(private val context: Context,
                              lifecycle: Lifecycle) :
        LifecycleObserver {
    private var progressDialog: ProgressDialog? = null

    /*
  Provide the public functions for the external call to cancel or reopen the progress dialog
   */
    fun showLoading() {
        if (progressDialog == null || !progressDialog!!.isShowing) {
            progressDialog = ProgressDialog(context)
            progressDialog!!.show()
            if (progressDialog!!.window != null) {
                progressDialog!!.window!!
                        .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            progressDialog!!.setContentView(R.layout.progress_dialog)
            progressDialog!!.isIndeterminate = true
            progressDialog!!.setCanceledOnTouchOutside(false)
        }
    }

    fun hideLoading() {
        if (progressDialog != null) {
            progressDialog!!.cancel()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        hideLoading()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        hideLoading()
    }

    init {
        lifecycle.addObserver(this)
    }
}