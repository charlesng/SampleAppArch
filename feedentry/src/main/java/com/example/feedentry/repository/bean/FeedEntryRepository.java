package com.example.feedentry.repository.bean;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import com.example.feedentry.datasources.room.AppDatabase;
import com.example.feedentry.datasources.room.FeedEntryDAO;
import java.util.List;
import javax.inject.Singleton;

/**
 * Created by Charles Ng on 6/9/2017.
 * Implements the FeedEntryDAO interface to handle all the situation
 */

@Singleton
public class FeedEntryRepository extends LiveData<List<FeedEntry>> implements FeedEntryDAO {

  private FeedEntryDAO feedEntryDAO;


  public void init(Context context) {
    AppDatabase db = AppDatabase.getDb(context);
    feedEntryDAO = db.feedEntryDao();
  }

  public static FeedEntryRepository getInstance(Context context
  ) {
    FeedEntryRepository feedEntryRepository = new FeedEntryRepository();
    feedEntryRepository.init(context);
    return feedEntryRepository;
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
