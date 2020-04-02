package com.cn29.aac.ui.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cn29.aac.repo.user.AuthRepository
import com.cn29.aac.repo.user.AuthRepository.AuthMode
import com.cn29.aac.repo.user.LoginBean
import com.cn29.aac.util.Result

/**
 * Created by Charles Ng on 13/10/2017.
 */
class LoginViewModel(private val authRepository: AuthRepository) :
        ViewModel() {
    fun login(authMode: AuthMode,
              email: String,
              password: String): LiveData<Result<LoginBean>> {
        return authRepository.login(authMode, email, password)
    }

    val isLogin: Boolean
        get() = authRepository.isLogin

}