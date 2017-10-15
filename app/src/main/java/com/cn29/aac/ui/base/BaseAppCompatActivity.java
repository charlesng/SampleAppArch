package com.cn29.aac.ui.base;

import android.os.Bundle;
import com.cn29.aac.ui.common.AlertDialogComponent;
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback;
import com.cn29.aac.ui.common.ProgressDialogComponent;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Charles Ng on 11/10/2017.
 */

public class BaseAppCompatActivity extends DaggerAppCompatActivity {

  protected ProgressDialogComponent progressDialogComponent;
  protected AlertDialogComponent dialogComponent;
  //permission callback
  private PermissionCallback permissionCallback = (requestCode, permissions, grantResults) -> {
  };


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    progressDialogComponent = new ProgressDialogComponent(this, this.getLifecycle());
    dialogComponent = new AlertDialogComponent(this, this.getLifecycle());
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
      String permissions[], int[] grantResults) {
    permissionCallback.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  public PermissionCallback getPermissionCallback() {
    return permissionCallback;
  }

  public void setPermissionCallback(
      PermissionCallback permissionCallback) {
    this.permissionCallback = permissionCallback;
  }
}
