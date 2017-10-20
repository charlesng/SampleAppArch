package com.cn29.aac.ui.login;

import android.Manifest.permission;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityLoginBinding;
import com.cn29.aac.repo.user.LoginBean;
import com.cn29.aac.ui.common.ActivityPermissionComponent;
import com.cn29.aac.ui.common.ActivityPermissionComponentBuilder;
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback;
import com.cn29.aac.ui.login.vm.LoginViewModel;
import com.cn29.aac.ui.login.vm.LoginViewModelFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 13/10/2017.
 */

@Module
public class LoginActivityModule {


  @Provides
  LoginViewModel provideLoginVm(LoginActivity activity, LoginViewModelFactory factory) {
    return ViewModelProviders.of(activity, factory).get(LoginViewModel.class);
  }

  @Provides
  LoginBean provideDefaultLoginBean() {
    LoginBean loginBean = new LoginBean("com.cn29.acc@example.com", "MyCompany");
    loginBean.setPassword("HelloWorld");
    return loginBean;
  }

  @Provides
  ActivityLoginBinding provideBinding(LoginBean loginBean, LoginActivity activity) {
    ActivityLoginBinding activityLoginBinding = DataBindingUtil
        .setContentView(activity, R.layout.activity_login);
    activityLoginBinding.setLoginBean(loginBean);
    return activityLoginBinding;
  }

  @Provides
  ActivityPermissionComponent providePermission(LoginActivity activity) {
    return new ActivityPermissionComponentBuilder(activity)
        .setPermissions(new String[]{permission.READ_CONTACTS}).setRequestCode(200)
        .createActivityPermissionComponent();
  }

  @Provides
  PermissionCallback providePermissionCallBack(LoginActivity activity) {
    activity.setPermissionCallback((requestCode, permissions, grantResults) -> {
      switch (requestCode) {
        case 200:
          break;
        default:
          break;
      }
    });
    return activity.getPermissionCallback();
  }
}
