package com.cn29.aac.di.ui

import com.cn29.aac.ui.feedentry.FeedActivity
import com.cn29.aac.ui.feedentry.FeedActivityModule
import com.cn29.aac.ui.feedentry.FeedEntryFragment
import com.cn29.aac.ui.feedentry.FeedEntryFragmentModule
import com.cn29.aac.ui.feedentrydetail.FeedEntryDetailActivity
import com.cn29.aac.ui.feedentrydetail.FeedEntryDetailActivityModule
import com.cn29.aac.ui.location.LocationActivity
import com.cn29.aac.ui.location.LocationActivityModule
import com.cn29.aac.ui.location.LocationFragment
import com.cn29.aac.ui.location.LocationFragmentModule
import com.cn29.aac.ui.login.LoginActivity
import com.cn29.aac.ui.login.LoginActivityModule
import com.cn29.aac.ui.main.AppArchNavigationDrawer
import com.cn29.aac.ui.main.AppArchNavigationDrawerModule
import com.cn29.aac.ui.masterdetail.*
import com.cn29.aac.ui.shopping.*
import com.cn29.aac.ui.shoppingkart.ShoppingKartActivity
import com.cn29.aac.ui.shoppingkart.ShoppingKartActivityModule
import com.cn29.aac.ui.viewpager.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Charles Ng on 27/9/2017.
 */
@Module
abstract class UiBuilder {
    //Login
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun bindLoginActivity(): LoginActivity?

    //Main Activity
    @ContributesAndroidInjector(modules = [AppArchNavigationDrawerModule::class])
    abstract fun bindDrawer(): AppArchNavigationDrawer?

    //feed entry
    @ContributesAndroidInjector(modules = [FeedActivityModule::class])
    abstract fun bindFeedActivity(): FeedActivity?

    @ContributesAndroidInjector(modules = [FeedEntryDetailActivityModule::class])
    abstract fun bindFeedEntryDetailActivity(): FeedEntryDetailActivity?

    @ContributesAndroidInjector(modules = [FeedEntryFragmentModule::class])
    abstract fun bindFeedEntryFragment(): FeedEntryFragment?

    //location
    @ContributesAndroidInjector(modules = [LocationActivityModule::class])
    abstract fun bindLocationActivity(): LocationActivity?

    @ContributesAndroidInjector(modules = [LocationFragmentModule::class])
    abstract fun bindLocationFragment(): LocationFragment?

    //view pager
    @ContributesAndroidInjector(modules = [PagerActivityModule::class])
    abstract fun bindPagerActivity(): PagerActivity?

    @ContributesAndroidInjector(modules = [BlankFragmentAModule::class])
    abstract fun bindBlankFragmentA(): BlankFragmentA?

    @ContributesAndroidInjector(modules = [BlankFragmentBModule::class])
    abstract fun bindBlankFragmentB(): BlankFragmentB?

    //master detail
    @ContributesAndroidInjector(modules = [SimpleListActivityModule::class])
    abstract fun bindSimpleListActivity(): SimpleListActivity?

    @ContributesAndroidInjector(modules = [SimpleDetailActivityModule::class])
    abstract fun bindSimpleDetailActivity(): SimpleDetailActivity?

    @ContributesAndroidInjector(modules = [SimpleDetailFragmentModule::class])
    abstract fun bindSimpleDetailFragment(): SimpleDetailFragment?

    // Shopping
    @ContributesAndroidInjector(modules = [ShoppingActivityModule::class])
    abstract fun bindShoppingActivity(): ShoppingActivity?

    @ContributesAndroidInjector(modules = [ArtistDetailActivityModule::class])
    abstract fun bindArtistDetailActivity(): ArtistDetailActivity?

    @ContributesAndroidInjector(modules = [AlbumFragmentModule::class])
    abstract fun bindAlbumFragment(): AlbumFragment?

    @ContributesAndroidInjector(modules = [ArtistFragmentModule::class])
    abstract fun bindArtistFragment(): ArtistFragment?

    // Shopping Kart
    @ContributesAndroidInjector(modules = [ShoppingKartActivityModule::class])
    abstract fun bindShoppingKartActivity(): ShoppingKartActivity?
}