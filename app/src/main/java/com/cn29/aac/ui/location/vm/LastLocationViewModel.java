package com.cn29.aac.ui.location.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;
import com.cn29.aac.repo.itunes.Artist;
import com.cn29.aac.repo.itunes.ItunesRepository;
import com.cn29.aac.repo.location.LastLocationListener;
import com.cn29.aac.repo.util.Resource;
import java.util.List;


/**
 * Created by Charles Ng on 7/9/2017.
 */

public class LastLocationViewModel extends ViewModel {

  private LastLocationListener lastLocationListener;
  private ItunesRepository itunesRepository;


  public LastLocationViewModel(LastLocationListener lastLocationListener,
      ItunesRepository itunesRepository) {
    this.lastLocationListener = lastLocationListener;
    this.itunesRepository = itunesRepository;
  }

  public LiveData<Location> getLastKnowLocation() {
    return lastLocationListener;

  }

  public LiveData<Resource<List<Artist>>> getSearchResult(String searchTerm) {
    return this.itunesRepository.getArtistResult(searchTerm);
  }
}
