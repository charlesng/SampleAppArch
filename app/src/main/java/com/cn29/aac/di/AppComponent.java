package com.cn29.aac.di;

import android.app.Application;

import com.cn29.aac.MyApplication;
import com.cn29.aac.di.datasources.db.RoomModule;
import com.cn29.aac.di.datasources.remote.AuthModule;
import com.cn29.aac.di.datasources.remote.RESTModule;
import com.cn29.aac.di.repo.RepoModule;
import com.cn29.aac.di.ui.UiBuilder;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;


/**
 * Created by Charles Ng on 27/9/2017.
 */
@Singleton
@Component(modules = {
    //necessary modules
    AndroidInjectionModule.class, AndroidSupportInjectionModule.class,
    //app modules
    AppModule.class,
    //repo modules
    RepoModule.class,
    //datasource modules
    RESTModule.class,
    RoomModule.class,
    AuthModule.class,
    //ui modules
        UiBuilder.class
})
public interface AppComponent {

  void inject(MyApplication app);

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}