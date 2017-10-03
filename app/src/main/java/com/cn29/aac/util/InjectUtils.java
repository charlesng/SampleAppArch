package com.cn29.aac.util;

import android.content.Context;
import com.cn29.aac.repo.location.LastLocationListener;
import com.cn29.aac.ui.location.vm.LastLocationViewModelFactory;


/**
 * Created by Charles Ng on 20/9/2017.
 */

public class InjectUtils {

  private static LastLocationListener getLastLocationListener(Context context) {
    return new LastLocationListener(context);
  }

  public static LastLocationViewModelFactory provideLastLocationViewModelFactory(Context context) {
    LastLocationListener lastLocationListener = getLastLocationListener(context);
    return new LastLocationViewModelFactory(lastLocationListener);
  }
}
