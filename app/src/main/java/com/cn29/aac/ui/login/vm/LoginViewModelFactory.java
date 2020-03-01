package com.cn29.aac.ui.login.vm;

import com.cn29.aac.repo.user.AuthRepository;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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
