package com.cn29.aac.ui.common

import androidx.fragment.app.Fragment

class PermissionComponentBuilder(private val fragment: Fragment) {
    private lateinit var permissions: Array<String>
    private var requestCode = 0
    fun setPermissions(permissions: Array<String>): PermissionComponentBuilder {
        this.permissions = permissions
        return this
    }

    fun setRequestCode(requestCode: Int): PermissionComponentBuilder {
        this.requestCode = requestCode
        return this
    }

    fun createPermissionComponent(): FragmentPermissionComponent {
        return FragmentPermissionComponent(fragment, permissions, requestCode)
    }

}