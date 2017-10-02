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
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Charles Ng on 27/9/2017.
 */

@Module
public abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = FeedActivityModule.class)
  abstract FeedActivity bindFeedActivity();

  @ContributesAndroidInjector(modules = FeedEntryDetailActivityModule.class)
  abstract FeedEntryDetailActivity bindFeedEntryDetailActivity();

  @ContributesAndroidInjector(modules = FeedEntryFragmentModule.class)
  abstract FeedEntryFragment bindFeedEntryFragment();

  @ContributesAndroidInjector(modules = LocationActivityModule.class)
  abstract LocationActivity bindLocationActivity();

  @ContributesAndroidInjector(modules = LocationFragmentModule.class)
  abstract LocationFragment bindLocationFragment();
}