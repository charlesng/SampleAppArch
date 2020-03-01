package com.cn29.aac.ui.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.cn29.aac.R;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by Charles Ng on 11/10/2017.
 */

public class ProgressDialogComponent implements LifecycleObserver {

  private Context context;
  private ProgressDialog progressDialog;

  public ProgressDialogComponent(Context context, Lifecycle lifecycle) {
    this.context = context;
    lifecycle.addObserver(this);
  }

  /*
  Provide the public functions for the external call to cancel or reopen the progress dialog
   */
  public void showLoading() {
    if (progressDialog == null || !progressDialog.isShowing()) {
      progressDialog = new ProgressDialog(context);
      progressDialog.show();
      if (progressDialog.getWindow() != null) {
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      }
      progressDialog.setContentView(R.layout.progress_dialog);
      progressDialog.setIndeterminate(true);
      progressDialog.setCanceledOnTouchOutside(false);
    }
  }

  public void hideLoading() {
    if (progressDialog != null) {
      progressDialog.cancel();
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate() {
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  void onPause() {
    hideLoading();
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  void stop() {
    hideLoading();
  }

}
