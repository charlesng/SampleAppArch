package com.cn29.aac.ui.common;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Charles Ng on 13/10/2017.
 */

public class ActivityPermissionComponent implements LifecycleObserver {

  private final Activity activity;
  private String[] permissions;
  private int requestCode;


  public ActivityPermissionComponent(AppCompatActivity activity, String[] permissions,
      int requestCode) {
    this.activity = activity;
    this.permissions = permissions;
    this.requestCode = requestCode;
//    activity.getLifecycle().addObserver(this);
  }


  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate() {

  }


}
