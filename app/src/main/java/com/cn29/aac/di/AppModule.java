package com.cn29.aac.di;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Charles Ng on 27/9/2017.
 */


@Module
public class AppModule {

  @Singleton
  @Provides
  Context provideContext(Application application) {
    return application;
  }


}