package com.cn29.aac.ui.base;

import android.os.Bundle;

import com.cn29.aac.ui.common.AlertDialogComponent;
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback;
import com.cn29.aac.ui.common.ProgressDialogComponent;

import androidx.annotation.Nullable;
import dagger.android.support.DaggerFragment;

/**
 * Created by charlesng0209 on 2/10/2017.
 */

public class BaseFragment extends DaggerFragment {

  //common UI component
  protected ProgressDialogComponent progressDialogComponent;
  protected AlertDialogComponent dialogComponent;
  //permission callback
  private PermissionCallback permissionCallback;

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    progressDialogComponent = new ProgressDialogComponent(getActivity(), this.getLifecycle());
    dialogComponent = new AlertDialogComponent(getActivity(), this.getLifecycle());
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
