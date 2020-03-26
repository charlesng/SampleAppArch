package com.cn29.aac.repo.util;

import com.cn29.aac.AppExecutors;
import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.repo.util.GenericNetworkBoundResource.FetchFailed;
import com.cn29.aac.repo.util.GenericNetworkBoundResource.ShouldFetch;
import com.cn29.aac.repo.util.GenericNetworkBoundResource.WritetToDb;

import androidx.lifecycle.LiveData;

public class GenericNetworkBoundResourceBuilder<ResultType, NetworkRequestType> {

  private AppExecutors appExecutors;
  private LiveData<ResultType> dbSource;
  private LiveData<ApiResponse<NetworkRequestType>> networkSource;
  private ShouldFetch<ResultType> resultTypeShouldFetch;
  private WritetToDb<NetworkRequestType> networkRequestTypeWritetToDb;
  private FetchFailed fetchFailed;

  public GenericNetworkBoundResourceBuilder<ResultType, NetworkRequestType> setAppExecutors(
      AppExecutors appExecutors) {
    this.appExecutors = appExecutors;
    return this;
  }

  public GenericNetworkBoundResourceBuilder<ResultType, NetworkRequestType> setDbSource(
      LiveData<ResultType> dbSource) {
    this.dbSource = dbSource;
    return this;
  }

  public GenericNetworkBoundResourceBuilder<ResultType, NetworkRequestType> setNetworkSource(
      LiveData<ApiResponse<NetworkRequestType>> networkSource) {
    this.networkSource = networkSource;
    return this;
  }

  public GenericNetworkBoundResourceBuilder<ResultType, NetworkRequestType> setResultTypeShouldFetch(
      ShouldFetch<ResultType> resultTypeShouldFetch) {
    this.resultTypeShouldFetch = resultTypeShouldFetch;
    return this;
  }

  public GenericNetworkBoundResource<ResultType, NetworkRequestType> createGenericNetworkBoundResource() {
    return new GenericNetworkBoundResource<>(appExecutors, dbSource, networkSource,
        resultTypeShouldFetch, networkRequestTypeWritetToDb, fetchFailed);
  }

  public GenericNetworkBoundResourceBuilder<ResultType, NetworkRequestType> setNetworkRequestTypeWritetToDb(
      WritetToDb<NetworkRequestType> networkRequestTypeWritetToDb) {
    this.networkRequestTypeWritetToDb = networkRequestTypeWritetToDb;
    return this;
  }

  public GenericNetworkBoundResourceBuilder<ResultType, NetworkRequestType> setFetchFailed(
      FetchFailed fetchFailed) {
    this.fetchFailed = fetchFailed;
    return this;
  }
}