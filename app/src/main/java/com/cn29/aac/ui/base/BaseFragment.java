package com.cn29.aac.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.cn29.aac.ui.common.ProgressDialogComponent;
import dagger.android.support.DaggerFragment;

/**
 * Created by charlesng0209 on 2/10/2017.
 */

public class BaseFragment extends DaggerFragment {

  protected ProgressDialogComponent progressDialogComponent;

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    progressDialogComponent = new ProgressDialogComponent(getActivity(), this.getLifecycle());
  }

}
