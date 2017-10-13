package com.cn29.aac.di;

import android.app.Application;
import com.cn29.aac.datasource.auth.remote.FacebookAuth;
import com.cn29.aac.datasource.auth.remote.GoogleAuth;
import com.cn29.aac.datasource.auth.remote.MyCompanyAuth;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Charles Ng on 13/10/2017.
 */

@Module
public class AuthModule {

  @Singleton
  @Provides
  GoogleAuth provideGoogleAuth(Application application) {
    return new GoogleAuth();
  }

  @Singleton
  @Provides
  FacebookAuth provideFacebookAuth(Application application) {
    return new FacebookAuth();
  }

  @Singleton
  @Provides
  MyCompanyAuth provideMyCompanyAuth(Application application) {
    return new MyCompanyAuth();
  }

}
