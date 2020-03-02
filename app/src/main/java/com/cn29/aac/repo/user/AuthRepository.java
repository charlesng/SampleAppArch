package com.cn29.aac.repo.user;

import android.content.SharedPreferences;

import com.cn29.aac.AppExecutors;
import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.datasource.auth.db.AuthDao;
import com.cn29.aac.datasource.auth.remote.FacebookAuth;
import com.cn29.aac.datasource.auth.remote.GoogleAuth;
import com.cn29.aac.datasource.auth.remote.MyCompanyAuth;
import com.cn29.aac.repo.util.NetworkBoundResource;
import com.cn29.aac.repo.util.RateLimiter;
import com.cn29.aac.repo.util.Resource;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

/**
 * Created by Charles Ng on 13/10/2017.
 */

@Singleton
public class AuthRepository {


  private RateLimiter<String> repoListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);
  private AppExecutors appExecutors;
  private AuthDao authDao;
  private GoogleAuth googleAuth;
  private FacebookAuth facebookAuth;
  private MyCompanyAuth myCompanyAuth;
  private SharedPreferences sharedPreferences;


  @Inject
  public AuthRepository(AppExecutors appExecutors, SharedPreferences sharedPreferences,
      AuthDao authDao, GoogleAuth googleAuth,
      FacebookAuth facebookAuth,
      MyCompanyAuth myCompanyAuth) {
    this.appExecutors = appExecutors;
    this.authDao = authDao;
    this.sharedPreferences = sharedPreferences;
    this.googleAuth = googleAuth;
    this.facebookAuth = facebookAuth;
    this.myCompanyAuth = myCompanyAuth;
  }

  public LiveData<Resource<LoginBean>> login(AuthMode mode, String email, String password) {
    return new NetworkBoundResource<LoginBean, LoginBean>(appExecutors) {
      @Override
      protected void saveCallResult(@NonNull LoginBean item) {
        //save to db;
        authDao.insert(item);
        sharedPreferences.edit().putBoolean("isLogin", true).apply();
      }

      @Override
      protected boolean shouldFetch(@Nullable LoginBean data) {
        return data == null || data.getIsLogin() != 0;
      }

      @NonNull
      @Override
      protected LiveData<LoginBean> loadFromDb() {
        return authDao.getLogin(email);
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<LoginBean>> createCall() {
        switch (mode) {
          case GOOGLE:
              return googleAuth.getLogin(email);
          case FACEBOOK:
              return facebookAuth.getLogin(email);
          case MYCOMPANY:
              return myCompanyAuth.getLogin(email);
        }
        return null;
      }
    }.asLiveData();
  }

  public boolean isLogin() {
    return sharedPreferences.getBoolean("isLogin", false);
  }


  public enum AuthMode {
    GOOGLE, FACEBOOK, MYCOMPANY
  }
}
