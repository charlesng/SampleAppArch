package com.example.feedentry.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
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
  private MutableLiveData<CompositeModel> compositeModelLiveData;

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

  public FeedEntryListViewModel(LifecycleOwner lifecycleOwner,
      FeedEntryRepository feedEntryDBRepository) {
    this.feedEntryDBRepository = feedEntryDBRepository;
    this.feedEntries = feedEntryDBRepository.getAll();
    this.compositeModelLiveData = new MutableLiveData<>();
    //initialize the composite model to avoid NullPointerException
    this.compositeModelLiveData.postValue(new CompositeModel());
    
    this.feedEntries.observe(lifecycleOwner, list -> {
      CompositeModel compositeModel = compositeModelLiveData.getValue();
      compositeModel.setFeedEntries(list);
      compositeModelLiveData.postValue(compositeModel);
    });
    //Fake User Information live Data
    this.userId.observe(lifecycleOwner, defaultImageUrl ->
    {
      CompositeModel compositeModel = compositeModelLiveData.getValue();
      compositeModel.setUserId(defaultImageUrl);
      compositeModelLiveData.postValue(compositeModel);
    });

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
