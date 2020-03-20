package com.cn29.aac.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import com.cn29.aac.ui.common.AlertDialogComponent
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback
import com.cn29.aac.ui.common.ProgressDialogComponent
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Charles Ng on 11/10/2017.
 */
@SuppressLint("Registered")
open class BaseAppCompatActivity : DaggerAppCompatActivity() {
    @JvmField
    protected var progressDialogComponent: ProgressDialogComponent? = null
    private var dialogComponent: AlertDialogComponent? = null

    //permission callback
    protected var permissionCallback = object : PermissionCallback {
        override fun onRequestPermissionsResult(requestCode: Int,
                                                permissions: Array<String>,
                                                grantResults: IntArray) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialogComponent = ProgressDialogComponent(this,
                                                          this.lifecycle)
        dialogComponent = AlertDialogComponent(this, this.lifecycle)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        permissionCallback.onRequestPermissionsResult(requestCode,
                                                      permissions,
                                                      grantResults)
    }

}