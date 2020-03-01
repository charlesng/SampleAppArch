package com.cn29.aac.ui.common;

import androidx.fragment.app.Fragment;

public class PermissionComponentBuilder {

  private final Fragment fragment;
  private String[] permissions;
  private int requestCode;

  public PermissionComponentBuilder(Fragment fragment) {
    this.fragment = fragment;
  }


  public PermissionComponentBuilder setPermissions(String[] permissions) {
    this.permissions = permissions;
    return this;
  }

  public PermissionComponentBuilder setRequestCode(int requestCode) {
    this.requestCode = requestCode;
    return this;
  }

  public FragmentPermissionComponent createPermissionComponent() {
    return new FragmentPermissionComponent(fragment, permissions, requestCode);
  }
}