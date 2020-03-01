package com.cn29.aac.di.datasources.db;

import android.app.Application;

import com.cn29.aac.datasource.auth.db.AuthDao;
import com.cn29.aac.datasource.auth.db.AuthDb;
import com.cn29.aac.datasource.github.db.GithubDb;
import com.cn29.aac.datasource.github.db.RepoDao;
import com.cn29.aac.datasource.github.db.UserDao;
import com.cn29.aac.datasource.itunes.db.AlbumDao;
import com.cn29.aac.datasource.itunes.db.ArtistDao;
import com.cn29.aac.datasource.itunes.db.ItunesDb;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 16/10/2017.
 */

@Module
public class RoomModule {

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


  //Itunes Db instance and Dao
  @Singleton
  @Provides
  ItunesDb provideItunesDb(Application application) {
    return Room.databaseBuilder(application, ItunesDb.class, "itunes.db").build();
  }

  @Singleton
  @Provides
  ArtistDao provideArtisDao(ItunesDb itunesDb) {
    return itunesDb.artistDao();
  }

  @Singleton
  @Provides
  AlbumDao provideAlbumDao(ItunesDb itunesDb) {
    return itunesDb.albumDao();
  }
}
