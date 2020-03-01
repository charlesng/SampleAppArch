package com.cn29.aac.di.datasources.remote;

import com.cn29.aac.datasource.api.LiveDataCallAdapterFactory;
import com.cn29.aac.datasource.github.remote.GithubService;
import com.cn29.aac.datasource.itunes.remote.ItunesService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Named;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
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
  Retrofit provideGithubRetrofit(OkHttpClient okHttpClient) {
    Retrofit retrofit = new Retrofit.Builder()
        .client(okHttpClient)
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
  Retrofit provideItunesRetrofit(OkHttpClient okHttpClient) {
    Retrofit retrofit = new Retrofit.Builder()
        .client(okHttpClient)
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

  @NonNull
  @Provides
  @Singleton
  OkHttpClient provideOKHttpClient() {
    return new OkHttpClient.Builder()
        .addNetworkInterceptor(new StethoInterceptor())
        .build();
  }
}
