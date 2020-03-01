package com.cn29.aac.repo.user;

/**
 * Created by Charles Ng on 13/10/2017.
 */

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;

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

  private String password;

  public LoginBean(@NonNull String email, String authType) {
    this.email = email;
    this.authType = authType;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
