package com.example.feedentry.repository.bean;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import com.example.feedentry.datasources.room.AppDatabase;
import com.example.feedentry.datasources.room.FeedEntryDAO;
import java.util.List;

/**
 * Created by Charles Ng on 6/9/2017.
 */

public class FeedEntryRepository extends LiveData<List<FeedEntry>> implements FeedEntryDAO {

  private FeedEntryDAO feedEntryDAO;

  public void init(Context context) {
    AppDatabase db = AppDatabase.getDb(context);
    feedEntryDAO = db.feedEntryDao();
  }

  @Override
  public LiveData<List<FeedEntry>> getAll() {
    return feedEntryDAO.getAll();
  }

  @Override
  public List<FeedEntry> loadAllByIds(int[] userIds) {
    return null;
  }

  @Override
  public FeedEntry findByName(String title, String subTitle) {
    return null;
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
