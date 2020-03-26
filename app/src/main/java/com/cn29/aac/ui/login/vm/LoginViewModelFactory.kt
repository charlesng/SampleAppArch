package com.cn29.aac.ui.login.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.cn29.aac.repo.user.AuthRepository
import javax.inject.Inject

/**
 * Created by Charles Ng on 13/10/2017.
 */
class LoginViewModelFactory @Inject constructor(private val authRepository: AuthRepository) :
        NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(authRepository) as T
    }

}