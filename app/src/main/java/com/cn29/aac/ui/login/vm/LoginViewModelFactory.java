package com.cn29.aac.ui.login.vm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.cn29.aac.repo.user.AuthRepository;
import javax.inject.Inject;

/**
 * Created by Charles Ng on 13/10/2017.
 */

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  private AuthRepository authRepository;

  @Inject
  public LoginViewModelFactory(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new LoginViewModel(authRepository);
  }
}
