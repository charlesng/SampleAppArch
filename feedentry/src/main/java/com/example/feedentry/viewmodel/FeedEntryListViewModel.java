package com.example.feedentry.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.repository.bean.FeedEntryRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles Ng on 6/9/2017.
 */

public class FeedEntryListViewModel extends ViewModel {

  //list all your live data here
  private LiveData<List<FeedEntry>> feedEntries = new MutableLiveData<>();
  private MutableLiveData<String> userId = new MutableLiveData<>();
  //show your composite model here
  private MediatorLiveData<CompositeModel> compositeModelLiveData;

  //list all your repository
  private FeedEntryRepository feedEntryDBRepository;

  /*
  The complete model to show the data
   */
  public static class CompositeModel {

    String userId = "SystemId";
    private List<FeedEntry> feedEntries = new ArrayList<>();


    public String getUserId() {
      return userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }

    public List<FeedEntry> getFeedEntries() {
      return feedEntries;
    }

    public void setFeedEntries(
        List<FeedEntry> feedEntries) {
      this.feedEntries = feedEntries;
    }


  }

  public FeedEntryListViewModel(
      FeedEntryRepository feedEntryDBRepository) {
    this.feedEntryDBRepository = feedEntryDBRepository;
    this.feedEntries = feedEntryDBRepository.getAll();
    this.compositeModelLiveData = new MediatorLiveData<>();
    this.compositeModelLiveData.addSource(feedEntries, data ->
    {
      CompositeModel compositeModel = compositeModelLiveData.getValue();
      compositeModel.setFeedEntries(data);
      compositeModelLiveData.postValue(compositeModel);
    });
    this.compositeModelLiveData.addSource(userId, data -> {
      CompositeModel compositeModel = compositeModelLiveData.getValue();
      compositeModel.setUserId(data);
      compositeModelLiveData.postValue(compositeModel);
    });
    //initialize the composite model to avoid NullPointerException
    this.compositeModelLiveData.postValue(new CompositeModel());
  }

  public void loadUserId(String userId) {
    this.userId.setValue(userId);
  }

  public LiveData<List<FeedEntry>> getFeedEntrys() {
    return feedEntryDBRepository.getAll();
  }

  public LiveData<CompositeModel> getCompositeEntrys() {
    return compositeModelLiveData;
  }

  public LiveData<List<FeedEntry>> insert(FeedEntry... feedEntries) {
    feedEntryDBRepository.insertAll(feedEntries);
    return feedEntryDBRepository.getAll();
  }

  public void delete(FeedEntry feedEntry) {
    feedEntryDBRepository.delete(feedEntry);
  }


  public int update(FeedEntry feedEntry) {
    return feedEntryDBRepository.update(feedEntry);
  }

}
