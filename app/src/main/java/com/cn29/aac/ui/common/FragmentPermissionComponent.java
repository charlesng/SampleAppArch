package com.cn29.aac.ui.common;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by Charles Ng on 12/10/2017.
 */

public class FragmentPermissionComponent implements LifecycleObserver {

  private final Fragment fragment;
  private String[] permissions;
  private int requestCode;


  public FragmentPermissionComponent(Fragment fragment, String[] permissions, int requestCode) {
    this.fragment = fragment;
    this.permissions = permissions;
    this.requestCode = requestCode;
    fragment.getLifecycle().addObserver(this);
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate() {
    fragment.requestPermissions(permissions, requestCode);
  }


  public interface PermissionCallback {

    void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults);
  }
}
