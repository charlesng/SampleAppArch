package sampleproj.charlesng0209.com.sampleapparch.architecture;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Charles Ng on 7/9/2017.
 */

public class MyApplication extends Application {

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }
}
