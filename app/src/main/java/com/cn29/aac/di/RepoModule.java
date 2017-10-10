package com.cn29.aac.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import com.cn29.aac.datasource.GithubDb;
import com.cn29.aac.datasource.LiveDataCallAdapterFactory;
import com.cn29.aac.datasource.RepoDao;
import com.cn29.aac.datasource.UserDao;
import com.cn29.aac.datasource.api.GithubService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by charlesng0209 on 2/10/2017.
 */


@Module
public class RepoModule {

  @Singleton
  @Provides
  Retrofit provideRetrofit() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(new LiveDataCallAdapterFactory())
        .build();
    return retrofit;
  }

  @Singleton
  @Provides
  GithubService provideGitHubService(Retrofit retrofit) {
    GithubService service = retrofit.create(GithubService.class);
    return service;
  }

  @Singleton
  @Provides
  GithubDb provideDb(Application app) {
    return Room.databaseBuilder(app, GithubDb.class, "github.db").build();
  }

  @Singleton
  @Provides
  UserDao provideUserDao(GithubDb db) {
    return db.userDao();
  }

  @Singleton
  @Provides
  RepoDao provideRepoDao(GithubDb db) {
    return db.repoDao();
  }
}
