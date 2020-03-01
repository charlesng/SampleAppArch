package com.cn29.aac.repo.itunes;

import com.cn29.aac.AppExecutors;
import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.datasource.itunes.db.AlbumDao;
import com.cn29.aac.datasource.itunes.db.ArtistDao;
import com.cn29.aac.datasource.itunes.remote.ItunesService;
import com.cn29.aac.repo.util.GenericNetworkBoundResourceBuilder;
import com.cn29.aac.repo.util.RateLimiter;
import com.cn29.aac.repo.util.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;

/**
 * Created by Charles Ng on 16/10/2017.
 */

@Singleton
public class ItunesRepository {

  // db

  private ArtistDao artistDao;

  private AlbumDao albumDao;

  // service
  private ItunesService service;

  private AppExecutors appExecutors;

  private RateLimiter<String> repoListRateLimit = new RateLimiter<>(10, TimeUnit.SECONDS);

  @Inject
  public ItunesRepository(
      ArtistDao artistDao, AlbumDao albumDao,
      ItunesService service,
      AppExecutors appExecutors) {
    this.artistDao = artistDao;
    this.albumDao = albumDao;
    this.service = service;
    this.appExecutors = appExecutors;
  }

  public LiveData<ApiResponse<ArtistSearchResult>> getArtistSearchResult(String searchTerm) {
    return service.getArtistSearchResult(searchTerm);
  }

  public LiveData<ApiResponse<AlbumSearchResult>> getAlbumSearchResult(int artistId,
      String entity) {
    return service.getAlbumSearchResult(artistId, entity);
  }

  //The following can provide the offline and online record
  public LiveData<Resource<List<Artist>>> getArtistResult(String searchTerm) {
    return new GenericNetworkBoundResourceBuilder<List<Artist>, ArtistSearchResult>()
        .setAppExecutors(appExecutors)
        .setDbSource(artistDao.getArtists(searchTerm))
        .setNetworkSource(service
            .getArtistSearchResult(searchTerm))
        .setResultTypeShouldFetch(
            data -> data == null || data.isEmpty() || repoListRateLimit.shouldFetch(searchTerm))
        .setNetworkRequestTypeWritetToDb(data -> artistDao.insert(data.getResults()))
        .setFetchFailed(() -> repoListRateLimit.reset(searchTerm))
        .createGenericNetworkBoundResource().asLiveData();
  }

  public LiveData<Resource<List<Album>>> getAlbumResult(int artistId, String entity) {
    return new GenericNetworkBoundResourceBuilder<List<Album>, AlbumSearchResult>()
        .setAppExecutors(appExecutors)
        .setDbSource(albumDao.get(artistId))
        .setNetworkSource(service.getAlbumSearchResult(artistId, entity))
        .setNetworkRequestTypeWritetToDb(
            item -> albumDao.insert(item.getResults()))
        .setResultTypeShouldFetch(data -> data == null || data.isEmpty() || repoListRateLimit
            .shouldFetch(artistId + entity))
        .setFetchFailed(() -> {
          repoListRateLimit.reset(artistId + entity);
        })
        .createGenericNetworkBoundResource().asLiveData();
  }


  public int starArtist(Artist artist) {
    //update the db only
    return artistDao.update(artist);
  }
}
