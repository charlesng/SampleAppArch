package com.example.feedentry.utils;

import android.content.Context;
import com.example.feedentry.repository.bean.FeedEntryRepository;
import com.example.feedentry.viewmodel.FeedEntryDetailViewModelFactory;
import com.example.feedentry.viewmodel.FeedEntryListViewModelFactory;

/**
 * Created by Charles Ng on 20/9/2017.
 */

public class InjectUtils {

  private static FeedEntryRepository provideRepository(Context context) {
    return FeedEntryRepository.getInstance(context);
  }

  public static FeedEntryListViewModelFactory provideFeedEntryListViewModelFactory(
      Context context) {
    FeedEntryRepository feedEntryRepository = provideRepository(context);
    return new FeedEntryListViewModelFactory(feedEntryRepository);
  }

  /*
  Provide the uid as parameter to fetch the data
   */
  public static FeedEntryDetailViewModelFactory provideFeenEntryDetailViewModelFactory(
      Context context, int uid) {
    FeedEntryRepository feedEntryRepository = provideRepository(context);
    return new FeedEntryDetailViewModelFactory(feedEntryRepository, uid);
  }
}
