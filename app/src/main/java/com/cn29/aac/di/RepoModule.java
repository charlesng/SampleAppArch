package com.cn29.aac.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import com.cn29.aac.datasource.api.LiveDataCallAdapterFactory;
import com.cn29.aac.datasource.auth.db.AuthDao;
import com.cn29.aac.datasource.auth.db.AuthDb;
import com.cn29.aac.datasource.github.db.GithubDb;
import com.cn29.aac.datasource.github.db.RepoDao;
import com.cn29.aac.datasource.github.db.UserDao;
import com.cn29.aac.datasource.github.remote.GithubService;
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

  //Retorift
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

  //RESTful service
  @Singleton
  @Provides
  GithubService provideGitHubService(Retrofit retrofit) {
    GithubService service = retrofit.create(GithubService.class);
    return service;
  }

  //Github Db instance and dao
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


  //Auth Db instance and Dao
  @Singleton
  @Provides
  AuthDb provideAuthDb(Application application) {
    return Room.databaseBuilder(application, AuthDb.class, "auth.db").build();
  }

  @Singleton
  @Provides
  AuthDao provideAuthDao(AuthDb authDb) {
    return authDb.authDao();
  }

}
