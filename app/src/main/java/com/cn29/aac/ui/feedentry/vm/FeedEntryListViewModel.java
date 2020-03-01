package com.cn29.aac.ui.feedentry.vm;

import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.repo.feedentry.FeedEntryRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


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
  //Data binding variable
  private ObservableField<FeedEntry> feedEntry;

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
    feedEntry = new ObservableField<>();
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

  public ObservableField<FeedEntry> getFeedEntry() {
    return feedEntry;
  }

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

}
