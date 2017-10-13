package com.cn29.aac.datasource.auth.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.repo.user.LoginBean;
import retrofit2.Response;

/**
 * Created by Charles Ng on 13/10/2017.
 */

public class GoogleAuth {

  public LiveData<ApiResponse<LoginBean>> getLogin(String email, String password) {
    MutableLiveData<ApiResponse<LoginBean>> liveData = new MutableLiveData<>();
    LoginBean loginBean = new LoginBean(email, "Google");
    loginBean.setIsLogin(1);
    liveData.setValue(new ApiResponse<>(Response.success(loginBean)));
    return liveData;
  }
}
