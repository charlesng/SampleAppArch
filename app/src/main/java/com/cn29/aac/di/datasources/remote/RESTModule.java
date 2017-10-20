package com.cn29.aac.di.datasources.remote;

import com.cn29.aac.datasource.api.LiveDataCallAdapterFactory;
import com.cn29.aac.datasource.github.remote.GithubService;
import com.cn29.aac.datasource.itunes.remote.ItunesService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Charles Ng on 16/10/2017.
 */

@Module
public class RESTModule {

  //Github
  @Provides
  @Named("Github")
  @Singleton
  Retrofit provideGithubRetrofit() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(new LiveDataCallAdapterFactory())
        .build();
    return retrofit;
  }

  //RESTful service
  @Provides
  @Singleton
  GithubService provideGitHubService(@Named("Github") Retrofit retrofit) {
    GithubService service = retrofit.create(GithubService.class);
    return service;
  }

  @Provides
  @Named("itunes")
  @Singleton
  Retrofit provideItunesRetrofit() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(new LiveDataCallAdapterFactory())
        .build();
    return retrofit;
  }

  @Provides
  @Singleton
  ItunesService provideItunesService(@Named("itunes") Retrofit retrofit) {
    ItunesService service = retrofit.create(ItunesService.class);
    return service;
  }
}
