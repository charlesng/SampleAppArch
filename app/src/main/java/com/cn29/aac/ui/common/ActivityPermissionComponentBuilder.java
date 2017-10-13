package com.cn29.aac.ui.common;

import android.support.v7.app.AppCompatActivity;

public class ActivityPermissionComponentBuilder {

  private final AppCompatActivity activity;
  private String[] permissions;
  private int requestCode;

  public ActivityPermissionComponentBuilder(AppCompatActivity activity) {
    this.activity = activity;
  }


  public ActivityPermissionComponentBuilder setPermissions(String[] permissions) {
    this.permissions = permissions;
    return this;
  }

  public ActivityPermissionComponentBuilder setRequestCode(int requestCode) {
    this.requestCode = requestCode;
    return this;
  }

  public ActivityPermissionComponent createActivityPermissionComponent() {
    return new ActivityPermissionComponent(activity, permissions, requestCode);
  }
}