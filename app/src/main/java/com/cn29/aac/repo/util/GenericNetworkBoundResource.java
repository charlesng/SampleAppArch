package com.cn29.aac.repo.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.cn29.aac.AppExecutors;
import com.cn29.aac.datasource.api.ApiResponse;
import java.util.Objects;

/**
 * Created by Charles Ng on 23/10/2017.
 */

public class GenericNetworkBoundResource<ResultType, NetworkRequestType> {

  private final AppExecutors appExecutors;

  private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

  private ShouldFetch<ResultType> shouldFetch;

  private WritetToDb<NetworkRequestType> networkRequestTypeWritetToDb;

  private FetchFailed fetchFailed;

  @MainThread
  public GenericNetworkBoundResource(AppExecutors appExecutors, LiveData<ResultType> dbSource,
      LiveData<ApiResponse<NetworkRequestType>> networkSource,
      ShouldFetch<ResultType> resultTypeShouldFetch,
      WritetToDb<NetworkRequestType> networkRequestTypeWritetToDb,
      FetchFailed fetchFailed) {
    this.appExecutors = appExecutors;
    this.networkRequestTypeWritetToDb = networkRequestTypeWritetToDb;
    this.shouldFetch = resultTypeShouldFetch;
    this.fetchFailed = fetchFailed;
    //init the result value first to make it auto trigger in onActive() function
    result.setValue(Resource.checking(null));
    result.addSource(dbSource, data -> {
      // remove the db source immediately
      result.removeSource(dbSource);
      if (shouldFetch.shouldFetch(data)) {
        result.addSource(dbSource, newData -> setValue(Resource.loading(newData)));
        result.addSource(networkSource, networkSourceData -> {
          result.removeSource(networkSource);
          result.removeSource(dbSource);
          //noinspection ConstantConditions
          if (networkSourceData.isSuccessful()) {
            appExecutors.diskIO().execute(() -> {
              this.networkRequestTypeWritetToDb.writeToDb(processResponse(networkSourceData));
              appExecutors.mainThread().execute(() ->
                  // we specially request a new live data,
                  // otherwise we will get immediately last cached value,
                  // which may not be updated with latest results received from network.
                  result.addSource(dbSource,
                      newData -> setValue(Resource.success(newData)))
              );
            });
          } else {
            onFetchFailed();
            result.addSource(dbSource,
                newData -> setValue(Resource.error(networkSourceData.errorMessage, newData)));
          }
        });
      } else {
        result.addSource(dbSource, newData -> setValue(Resource.success(newData)));
      }
    });
  }

  @WorkerThread
  protected NetworkRequestType processResponse(ApiResponse<NetworkRequestType> response) {
    return response.body;
  }


  public LiveData<Resource<ResultType>> asLiveData() {
    return result;
  }


  private void onFetchFailed() {
  }

  @MainThread
  private void setValue(Resource<ResultType> newValue) {
    if (!Objects.equals(result.getValue(), newValue)) {
      result.setValue(newValue);
    }
  }

  public interface ShouldFetch<ResultType> {

    boolean shouldFetch(ResultType resultType);
  }

  public interface WritetToDb<NetworkRequestType> {

    void writeToDb(NetworkRequestType networkRequestType);
  }

  public interface FetchFailed {

    void onFetchFailed();
  }

}
