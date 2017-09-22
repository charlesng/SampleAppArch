package sampleproj.charlesng0209.com.sampleapparch.architecture;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * Created by Charles Ng on 7/9/2017.
 */

public class MyApplication extends Application{

  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);

  }


}
