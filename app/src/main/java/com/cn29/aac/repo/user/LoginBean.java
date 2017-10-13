package com.cn29.aac.repo.user;

/**
 * Created by Charles Ng on 13/10/2017.
 */

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Auth",
    primaryKeys = {"email"})
public class LoginBean {


  @SerializedName("email")
  @NonNull
  private String email;
  @SerializedName("authType")
  private String authType;
  @SerializedName("isLogin")
  private int isLogin = 0;

  public LoginBean(@NonNull String email, String authType) {
    this.email = email;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  public String getAuthType() {
    return authType;
  }

  public void setAuthType(String authType) {
    this.authType = authType;
  }

  public int getIsLogin() {
    return isLogin;
  }

  public void setIsLogin(int isLogin) {
    this.isLogin = isLogin;
  }
}
