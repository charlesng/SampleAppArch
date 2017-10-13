package com.cn29.aac.ui.common;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v4.app.Fragment;

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
