package com.cn29.aac.repo.itunes;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.cn29.aac.AppExecutors;
import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.datasource.itunes.db.AlbumDao;
import com.cn29.aac.datasource.itunes.db.ArtistDao;
import com.cn29.aac.datasource.itunes.remote.ItunesService;
import com.cn29.aac.repo.util.NetworkBoundResource;
import com.cn29.aac.repo.util.Resource;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

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


  public LiveData<Resource<List<Artist>>> getArtistResult(String searchTerm) {
    return new NetworkBoundResource<List<Artist>, ArtistSearchResult>(appExecutors) {

      @Override
      protected void saveCallResult(@NonNull ArtistSearchResult item) {
        artistDao.insert(item.getResults().toArray(new Artist[item.getResults().size()]));
      }

      @Override
      protected boolean shouldFetch(@Nullable List<Artist> data) {
        return data == null || data.size() == 0;
      }

      @NonNull
      @Override
      protected LiveData<List<Artist>> loadFromDb() {
        return artistDao.getAll();
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<ArtistSearchResult>> createCall() {
        return service.getArtistSearchResult(searchTerm);
      }
    }.asLiveData();
  }

  public LiveData<Resource<List<Album>>> getAlbumResult(int artistId, String entity) {
    return new NetworkBoundResource<List<Album>, AlbumSearchResult>(appExecutors) {
      @Override
      protected void saveCallResult(@NonNull AlbumSearchResult item) {
        albumDao.insert(item.getResults().toArray(new Album[item.getResults().size()]));
      }

      @Override
      protected boolean shouldFetch(@Nullable List<Album> data) {
        return data == null || data.size() == 0;
      }

      @NonNull
      @Override
      protected LiveData<List<Album>> loadFromDb() {
        return albumDao.getAll();
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<AlbumSearchResult>> createCall() {
        return service.getAlbumSearchResult(artistId, entity);
      }
    }.asLiveData();
  }


  public int starArtist(Artist artist) {
    //update the db only
    return artistDao.update(artist);
  }
}
