package com.cn29.aac.di;

import android.app.Application;
import com.cn29.aac.repo.bean.FeedEntryRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by charlesng0209 on 2/10/2017.
 */


@Module
@Singleton
public class RepoModule {

  @Provides
  FeedEntryRepository provideFeedEntryRepository(Application application) {
    FeedEntryRepository feedEntryRepository = new FeedEntryRepository();
    feedEntryRepository.init(application);
    return feedEntryRepository;
  }


}
