package com.cn29.aac.repo.feedentry;

import android.app.Application;
import android.content.Context;

import com.cn29.aac.datasource.feedentry.db.AppDatabase;
import com.cn29.aac.datasource.feedentry.db.FeedEntryDAO;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;

/**
 * Created by Charles Ng on 6/9/2017.
 * Implements the FeedEntryDAO interface to handle all the situation
 */

@Singleton
public class FeedEntryRepository extends LiveData<List<FeedEntry>> implements FeedEntryDAO {

  private FeedEntryDAO feedEntryDAO;


  @Inject
  public FeedEntryRepository(Application application) {
    init(application);
  }
//  public static FeedEntryRepository getInstance(Context context) {
//    FeedEntryRepository feedEntryRepository = new FeedEntryRepository();
//    feedEntryRepository.init(context);
//    return feedEntryRepository;
//  }

  public void init(Context context) {
    AppDatabase db = AppDatabase.getDb(context);
    feedEntryDAO = db.feedEntryDao();
  }

  @Override
  public LiveData<List<FeedEntry>> getAll() {
    return feedEntryDAO.getAll();
  }

  @Override
  public LiveData<FeedEntry> findByUid(int uid) {
    return feedEntryDAO.findByUid(uid);
  }

  @Override
  public void insertAll(FeedEntry... feedEntries) {
    feedEntryDAO.insertAll(feedEntries);
  }

  @Override
  public void delete(FeedEntry feedEntry) {
    feedEntryDAO.delete(feedEntry);
  }

  public int update(FeedEntry feedEntry) {
    return feedEntryDAO.update(feedEntry);
  }
}
