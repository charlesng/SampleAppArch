package com.cn29.aac.ui.shopping.vm;

import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.repo.itunes.Artist;
import com.cn29.aac.repo.itunes.ArtistSearchResult;
import com.cn29.aac.repo.itunes.ItunesRepository;
import com.cn29.aac.repo.util.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Charles Ng on 20/10/2017.
 */

public class ArtistFragmentViewModel extends ViewModel {

  private ItunesRepository itunesRepository;

  public ArtistFragmentViewModel(ItunesRepository itunesRepository) {
    this.itunesRepository = itunesRepository;
  }

  public LiveData<ApiResponse<ArtistSearchResult>> getArtistSearchResult(String term) {
    return itunesRepository.getArtistSearchResult(term);
  }

  public int starArtist(Artist artist) {
    return itunesRepository.starArtist(artist);
  }

  public LiveData<Resource<List<Artist>>> getArtist(String term) {
    return itunesRepository.getArtistResult(term);
  }
}
