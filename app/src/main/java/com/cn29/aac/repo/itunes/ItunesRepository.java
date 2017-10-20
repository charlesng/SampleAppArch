package com.cn29.aac.repo.itunes;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.cn29.aac.AppExecutors;
import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.datasource.itunes.db.ArtistDao;
import com.cn29.aac.datasource.itunes.db.ItunesDb;
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
  private ItunesDb itunesDb;

  private ArtistDao artistDao;

  // service
  private ItunesService service;

  private AppExecutors appExecutors;

  @Inject
  public ItunesRepository(ItunesDb itunesDb,
      ArtistDao artistDao, ItunesService service,
      AppExecutors appExecutors) {
    this.itunesDb = itunesDb;
    this.artistDao = artistDao;
    this.service = service;
    this.appExecutors = appExecutors;
  }


  public LiveData<Resource<List<Artist>>> getArtistResult(String searchTerm) {

    return new NetworkBoundResource<List<Artist>, ArtistResult>(appExecutors) {

      @Override
      protected void saveCallResult(@NonNull ArtistResult item) {
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
      protected LiveData<ApiResponse<ArtistResult>> createCall() {
        return service.getArtist(searchTerm);
      }
    }.asLiveData();


  }
}
