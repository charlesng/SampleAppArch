package com.cn29.aac.ui.login

import android.Manifest.permission
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityLoginBinding
import com.cn29.aac.repo.user.LoginBean
import com.cn29.aac.ui.common.ActivityPermissionComponent
import com.cn29.aac.ui.common.ActivityPermissionComponentBuilder
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback
import com.cn29.aac.ui.login.vm.LoginViewModel
import com.cn29.aac.ui.login.vm.LoginViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Charles Ng on 13/10/2017.
 */
@Module
class LoginActivityModule {
    @Provides
    fun provideLoginVm(activity: LoginActivity,
                       factory: LoginViewModelFactory): LoginViewModel {
        return ViewModelProvider(activity, factory).get(
                LoginViewModel::class.java)
    }

    @Provides
    fun provideDefaultLoginBean(): LoginBean {
        val loginBean = LoginBean("com.cn29.acc@example.com",
                                  "MyCompany",
                                  0,
                                  "Testing1234")
        loginBean.password = "HelloWorld"
        return loginBean
    }

    @Provides
    fun provideBinding(loginBean: LoginBean,
                       activity: LoginActivity): ActivityLoginBinding {
        val activityLoginBinding: ActivityLoginBinding = DataBindingUtil
                .setContentView(activity, R.layout.activity_login)
        activityLoginBinding.loginBean = loginBean
        return activityLoginBinding
    }

    @Provides
    fun providePermission(activity: LoginActivity): ActivityPermissionComponent {
        return ActivityPermissionComponentBuilder(activity)
                .setPermissions(arrayOf(permission.READ_CONTACTS))
                .setRequestCode(200)
                .createActivityPermissionComponent()
    }

    @Provides
    fun providePermissionCallBack(): PermissionCallback {
        return object : PermissionCallback {
            override fun onRequestPermissionsResult(requestCode: Int,
                                                    permissions: Array<String>,
                                                    grantResults: IntArray) {

            }
        }
    }
}