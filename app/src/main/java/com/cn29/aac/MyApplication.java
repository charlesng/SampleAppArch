package com.cn29.aac;

import android.app.Activity;
import android.app.Application;
import com.cn29.aac.di.DaggerAppComponent;
import com.facebook.stetho.Stetho;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;


/**
 * Created by Charles Ng on 7/9/2017.
 */

public class MyApplication extends Application implements HasActivityInjector{

  @Inject
  DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    DaggerAppComponent.builder().application(this).build().inject(this);

  }
  @Override
  public AndroidInjector<Activity> activityInjector() {
    return activityDispatchingAndroidInjector;
  }

}
