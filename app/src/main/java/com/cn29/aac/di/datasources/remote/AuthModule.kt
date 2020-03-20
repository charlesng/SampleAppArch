package com.cn29.aac.di.datasources.remote

import android.app.Application
import com.cn29.aac.datasource.auth.remote.FacebookAuth
import com.cn29.aac.datasource.auth.remote.GoogleAuth
import com.cn29.aac.datasource.auth.remote.MyCompanyAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Charles Ng on 13/10/2017.
 */
@Module
class AuthModule {
    @Singleton
    @Provides
    fun provideGoogleAuth(application: Application?): GoogleAuth {
        return GoogleAuth()
    }

    @Singleton
    @Provides
    fun provideFacebookAuth(application: Application?): FacebookAuth {
        return FacebookAuth()
    }

    @Singleton
    @Provides
    fun provideMyCompanyAuth(application: Application?): MyCompanyAuth {
        return MyCompanyAuth()
    }
}