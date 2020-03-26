package com.cn29.aac.di.datasources.remote

import com.cn29.aac.datasource.api.LiveDataCallAdapterFactory
import com.cn29.aac.datasource.github.remote.GithubService
import com.cn29.aac.datasource.itunes.remote.ItunesService
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Charles Ng on 16/10/2017.
 */
@Module
class RESTModule {
    //Github
    @Provides
    @Named("Github")
    @Singleton
    fun provideGithubRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
    }

    //RESTful service
    @Provides
    @Singleton
    fun provideGitHubService(@Named("Github") retrofit: Retrofit): GithubService {
        return retrofit.create(
                GithubService::class.java)
    }

    @Provides
    @Named("itunes")
    @Singleton
    fun provideItunesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
    }

    @Provides
    @Singleton
    fun provideItunesService(@Named("itunes") retrofit: Retrofit): ItunesService {
        return retrofit.create(
                ItunesService::class.java)
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }
}