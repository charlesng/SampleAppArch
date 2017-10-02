package com.cn29.aac.di;

import android.app.Application;
import com.cn29.aac.MyApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;


/**
 * Created by Charles Ng on 27/9/2017.
 */

@Component(modules = {
    AndroidInjectionModule.class, AndroidSupportInjectionModule.class,
    AppModule.class,
    ActivityBuilder.class})
public interface AppComponent {

  void inject(MyApplication app);

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}