package com.cn29.aac.di

import android.app.Application
import com.cn29.aac.MyApplication
import com.cn29.aac.di.datasources.db.RoomModule
import com.cn29.aac.di.datasources.remote.AuthModule
import com.cn29.aac.di.datasources.remote.RESTModule
import com.cn29.aac.di.repo.RepoModule
import com.cn29.aac.di.ui.UiBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Charles Ng on 27/9/2017.
 */
@Singleton
@Component(modules = [ //necessary modules
    AndroidInjectionModule::class, AndroidSupportInjectionModule::class,  //app modules
    AppModule::class,  //repo modules
    RepoModule::class,  //datasource modules
    RESTModule::class, RoomModule::class, AuthModule::class,  //ui modules
    UiBuilder::class])
interface AppComponent {
    fun inject(app: MyApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}