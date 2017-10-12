package com.cn29.aac.ui.base;

import android.os.Bundle;
import com.cn29.aac.ui.common.ProgressDialogComponent;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Charles Ng on 11/10/2017.
 */

public class BaseAppCompatActivity extends DaggerAppCompatActivity {

  protected ProgressDialogComponent progressDialogComponent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    progressDialogComponent = new ProgressDialogComponent(this, this.getLifecycle());
    getLifecycle();
  }
}
