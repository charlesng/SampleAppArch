package com.example.feedentry.di;

import com.example.feedentry.ui.FeedActivity;
import com.example.feedentry.ui.FeedEntryDetailActivity;
import com.example.feedentry.ui.FeedEntryFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Charles Ng on 27/9/2017.
 */

@Module
public abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = FeedActivity.MyModule.class)
  abstract FeedActivity bindFeedActivity();

  @ContributesAndroidInjector(modules = FeedEntryDetailActivity.MyModule.class)
  abstract FeedEntryDetailActivity bindFeedEntryDetailActivity();

  @ContributesAndroidInjector(modules = FeedEntryFragment.MyModule.class)
  abstract FeedEntryFragment bindFeedEntryFragment();
}