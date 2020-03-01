package com.cn29.aac.ui.common;

import android.app.AlertDialog;
import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by Charles Ng on 12/10/2017.
 */

public class AlertDialogComponent implements LifecycleObserver {

  private Context context;
  private AlertDialog alertDialog;

  public AlertDialogComponent(Context context, Lifecycle lifecycle) {
    this.context = context;
    lifecycle.addObserver(this);
  }

  public void showDialog(AlertDialog alertDialog) {
    this.alertDialog = alertDialog;
    alertDialog.show();
  }

  public void hideDialog() {
    if (alertDialog != null) {
      alertDialog.dismiss();
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate() {
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  void onPause() {
    if (alertDialog != null) {
      alertDialog.dismiss();
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  void stop() {
    if (alertDialog != null) {
      alertDialog.dismiss();
    }

  }
}
