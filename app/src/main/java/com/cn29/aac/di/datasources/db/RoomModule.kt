package com.cn29.aac.di.datasources.db

import android.app.Application
import androidx.room.Room
import com.cn29.aac.datasource.auth.db.AuthDao
import com.cn29.aac.datasource.auth.db.AuthDb
import com.cn29.aac.datasource.github.db.GithubDb
import com.cn29.aac.datasource.github.db.RepoDao
import com.cn29.aac.datasource.github.db.UserDao
import com.cn29.aac.datasource.itunes.db.AlbumDao
import com.cn29.aac.datasource.itunes.db.ArtistDao
import com.cn29.aac.datasource.itunes.db.ItunesDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Charles Ng on 16/10/2017.
 */
@Module
class RoomModule {
    //Github Db instance and dao
    @Singleton
    @Provides
    fun provideDb(app: Application?): GithubDb {
        return Room.databaseBuilder(app!!,
                                    GithubDb::class.java,
                                    "github.db").build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: GithubDb): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun provideRepoDao(db: GithubDb): RepoDao {
        return db.repoDao()
    }

    //Auth Db instance and Dao
    @Singleton
    @Provides
    fun provideAuthDb(application: Application?): AuthDb {
        return Room.databaseBuilder(application!!,
                                    AuthDb::class.java,
                                    "auth.db").build()
    }

    @Singleton
    @Provides
    fun provideAuthDao(authDb: AuthDb): AuthDao {
        return authDb.authDao()
    }

    //Itunes Db instance and Dao
    @Singleton
    @Provides
    fun provideItunesDb(application: Application?): ItunesDb {
        return Room.databaseBuilder(application!!,
                                    ItunesDb::class.java,
                                    "itunes.db").build()
    }

    @Singleton
    @Provides
    fun provideArtisDao(itunesDb: ItunesDb): ArtistDao {
        return itunesDb.artistDao()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(itunesDb: ItunesDb): AlbumDao {
        return itunesDb.albumDao()
    }
}