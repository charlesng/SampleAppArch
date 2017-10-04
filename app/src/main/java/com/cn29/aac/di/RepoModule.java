package com.cn29.aac.di;

import android.app.Application;
import com.cn29.aac.datasource.LiveDataCallAdapterFactory;
import com.cn29.aac.datasource.api.GithubService;
import com.cn29.aac.repo.feedentry.FeedEntryRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

  @Provides
  Retrofit provideRetrofit() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(new LiveDataCallAdapterFactory())
        .build();
    return retrofit;
  }

  @Provides
  GithubService provideGitHubService(Retrofit retrofit) {
    GithubService service = retrofit.create(GithubService.class);
    return service;
  }
}
