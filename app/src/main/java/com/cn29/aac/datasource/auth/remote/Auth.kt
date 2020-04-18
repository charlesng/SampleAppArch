package com.cn29.aac.datasource.auth.remote

import com.cn29.aac.repo.user.LoginBean
import com.cn29.aac.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseAuth(protected val dispatcher: CoroutineContext) {
    abstract suspend fun getLogin(email: String,
                                  password: String): Result<LoginBean>
}

class MyCompanyAuth(dispatcher: CoroutineContext = Dispatchers.IO) :
        BaseAuth(dispatcher) {

    override suspend fun getLogin(email: String,
                                  password: String): Result<LoginBean> = withContext(
            this.dispatcher) {
        val loginBean = LoginBean(email!!, "MyCompany")
        loginBean.isLogin = 1
        Result.Success(loginBean)
    }
}

class FacebookAuth(dispatcher: CoroutineContext = Dispatchers.IO) :
        BaseAuth(dispatcher) {

    override suspend fun getLogin(email: String,
                                  password: String): Result<LoginBean> = withContext(
            this.dispatcher) {
        val loginBean = LoginBean(
                email!!,
                "Facebook")
        loginBean.isLogin = 1
        Result.Success(loginBean)
    }
}

class GoogleAuth(dispatcher: CoroutineContext = Dispatchers.IO) :
        BaseAuth(dispatcher) {

    override suspend fun getLogin(email: String,
                                  password: String): Result<LoginBean> = withContext(
            this.dispatcher) {
        val loginBean = LoginBean(
                email,
                "Google")
        loginBean.isLogin = 1
        Result.Success(loginBean)
    }
}