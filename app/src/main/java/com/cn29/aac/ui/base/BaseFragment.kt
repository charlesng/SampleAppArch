package com.cn29.aac.ui.base

import android.os.Bundle
import com.cn29.aac.ui.common.AlertDialogComponent
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback
import com.cn29.aac.ui.common.ProgressDialogComponent
import dagger.android.support.DaggerFragment

/**
 * Created by charlesng0209 on 2/10/2017.
 */
open class BaseFragment :
        DaggerFragment() {
    //common UI component
    @JvmField
    protected var progressDialogComponent: ProgressDialogComponent? = null
    private var dialogComponent: AlertDialogComponent? = null

    //permission callback
    private var permissionCallback: PermissionCallback? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressDialogComponent = activity?.let {
            ProgressDialogComponent(it,
                                    this.lifecycle)
        }
        dialogComponent = activity?.let {
            AlertDialogComponent(it,
                                 this.lifecycle)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        permissionCallback!!.onRequestPermissionsResult(requestCode,
                                                        permissions,
                                                        grantResults)
    }

}