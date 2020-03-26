package com.cn29.aac.datasource.auth.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cn29.aac.datasource.api.ApiResponse
import com.cn29.aac.repo.user.LoginBean
import retrofit2.Response

class FacebookAuth {
    fun getLogin(email: String?): LiveData<ApiResponse<LoginBean>> {
        val liveData = MutableLiveData<ApiResponse<LoginBean>>()
        val loginBean = LoginBean(email!!, "Facebook")
        loginBean.isLogin = 1
        liveData.value = ApiResponse(Response.success(loginBean))
        return liveData
    }
}