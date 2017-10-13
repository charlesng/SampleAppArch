package com.cn29.aac.di;


import com.cn29.aac.ui.feedentry.FeedActivity;
import com.cn29.aac.ui.feedentry.FeedActivityModule;
import com.cn29.aac.ui.feedentry.FeedEntryFragment;
import com.cn29.aac.ui.feedentry.FeedEntryFragmentModule;
import com.cn29.aac.ui.feedentrydetail.FeedEntryDetailActivity;
import com.cn29.aac.ui.feedentrydetail.FeedEntryDetailActivityModule;
import com.cn29.aac.ui.location.LocationActivity;
import com.cn29.aac.ui.location.LocationActivityModule;
import com.cn29.aac.ui.location.LocationFragment;
import com.cn29.aac.ui.location.LocationFragmentModule;
import com.cn29.aac.ui.login.LoginActivity;
import com.cn29.aac.ui.login.LoginActivityModule;
import com.cn29.aac.ui.main.AppArchNavigationDrawer;
import com.cn29.aac.ui.main.AppArchNavigationDrawerModule;
import com.cn29.aac.ui.masterdetail.SimpleDetailActivity;
import com.cn29.aac.ui.masterdetail.SimpleDetailActivityModule;
import com.cn29.aac.ui.masterdetail.SimpleDetailFragment;
import com.cn29.aac.ui.masterdetail.SimpleDetailFragmentModule;
import com.cn29.aac.ui.masterdetail.SimpleListActivity;
import com.cn29.aac.ui.masterdetail.SimpleListActivityModule;
import com.cn29.aac.ui.viewpager.BlankFragmentA;
import com.cn29.aac.ui.viewpager.BlankFragmentAModule;
import com.cn29.aac.ui.viewpager.BlankFragmentB;
import com.cn29.aac.ui.viewpager.BlankFragmentBModule;
import com.cn29.aac.ui.viewpager.PagerActivity;
import com.cn29.aac.ui.viewpager.PagerActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Charles Ng on 27/9/2017.
 */

@Module
public abstract class UiBuilder {


  //Login
  @ContributesAndroidInjector(modules = LoginActivityModule.class)
  abstract LoginActivity bindLoginActivity();

  //Main Activity
  @ContributesAndroidInjector(modules = AppArchNavigationDrawerModule.class)
  abstract AppArchNavigationDrawer bindDrawer();

  //feed entry
  @ContributesAndroidInjector(modules = FeedActivityModule.class)
  abstract FeedActivity bindFeedActivity();

  @ContributesAndroidInjector(modules = FeedEntryDetailActivityModule.class)
  abstract FeedEntryDetailActivity bindFeedEntryDetailActivity();

  @ContributesAndroidInjector(modules = FeedEntryFragmentModule.class)
  abstract FeedEntryFragment bindFeedEntryFragment();

  //location
  @ContributesAndroidInjector(modules = LocationActivityModule.class)
  abstract LocationActivity bindLocationActivity();

  @ContributesAndroidInjector(modules = LocationFragmentModule.class)
  abstract LocationFragment bindLocationFragment();

  //view pager
  @ContributesAndroidInjector(modules = PagerActivityModule.class)
  abstract PagerActivity bindPagerActivity();

  @ContributesAndroidInjector(modules = BlankFragmentAModule.class)
  abstract BlankFragmentA bindBlankFragmentA();

  @ContributesAndroidInjector(modules = BlankFragmentBModule.class)
  abstract BlankFragmentB bindBlankFragmentB();

  //master detail

  @ContributesAndroidInjector(modules = SimpleListActivityModule.class)
  abstract SimpleListActivity bindSimpleListActivity();

  @ContributesAndroidInjector(modules = SimpleDetailActivityModule.class)
  abstract SimpleDetailActivity bindSimpleDetailActivity();

  @ContributesAndroidInjector(modules = SimpleDetailFragmentModule.class)
  abstract SimpleDetailFragment bindSimpleDetailFragment();
}