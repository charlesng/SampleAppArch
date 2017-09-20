package com.example.location.util;

import android.content.Context;
import com.example.location.repository.LastLocationListener;
import com.example.location.viewmodel.LastLocationViewModelFactory;

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
