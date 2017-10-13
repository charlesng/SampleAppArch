package com.cn29.aac.ui.login.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.cn29.aac.repo.user.AuthRepository;
import com.cn29.aac.repo.user.AuthRepository.AuthMode;
import com.cn29.aac.repo.user.LoginBean;
import com.cn29.aac.repo.util.Resource;

/**
 * Created by Charles Ng on 13/10/2017.
 */

public class LoginViewModel extends ViewModel {

  private AuthRepository authRepository;


  public LoginViewModel(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  public LiveData<Resource<LoginBean>> login(AuthMode authMode, String email, String password) {
    return authRepository.login(authMode, email, password);
  }

  public boolean isLogin() {
    return authRepository.isLogin();
  }
}
