package com.cn29.aac.di;

import android.app.Application;
import android.content.Context;
import com.cn29.aac.repo.bean.FeedEntryRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Charles Ng on 27/9/2017.
 */

@Module
@Singleton
public class AppModule {

  @Provides
  Context provideContext(Application application) {
    return application;
  }

  @Provides
  FeedEntryRepository provideFeedEntryRepository(Application application)
  {
    FeedEntryRepository feedEntryRepository = new FeedEntryRepository();
    feedEntryRepository.init(application);
    return feedEntryRepository;
  }

}