package com.example.feedentry.di;

import com.example.feedentry.ui.FeedActivity;
import com.example.feedentry.ui.FeedActivity.MyModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Charles Ng on 27/9/2017.
 */

@Module
public abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = MyModule.class)
  abstract FeedActivity bindMainActivity();


}