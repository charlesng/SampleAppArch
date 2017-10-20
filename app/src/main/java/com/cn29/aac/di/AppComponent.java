package com.cn29.aac.di;

import android.app.Application;
import com.cn29.aac.MyApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;


/**
 * Created by Charles Ng on 27/9/2017.
 */
@Singleton
@Component(modules = {
    AndroidInjectionModule.class, AndroidSupportInjectionModule.class,
    AppModule.class,
    RepoModule.class,
    RESTModule.class,
    RoomModule.class,
    AuthModule.class,
    UiBuilder.class})
public interface AppComponent {

  void inject(MyApplication app);

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}