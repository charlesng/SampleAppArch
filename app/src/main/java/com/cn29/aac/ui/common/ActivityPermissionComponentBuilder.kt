package com.cn29.aac.ui.common

import androidx.appcompat.app.AppCompatActivity

class ActivityPermissionComponentBuilder(private val activity: AppCompatActivity) {
    private lateinit var permissions: Array<String>
    private var requestCode = 0
    fun setPermissions(permissions: Array<String>): ActivityPermissionComponentBuilder {
        this.permissions = permissions
        return this
    }

    fun setRequestCode(requestCode: Int): ActivityPermissionComponentBuilder {
        this.requestCode = requestCode
        return this
    }

    fun createActivityPermissionComponent(): ActivityPermissionComponent {
        return ActivityPermissionComponent(activity, permissions, requestCode)
    }

}