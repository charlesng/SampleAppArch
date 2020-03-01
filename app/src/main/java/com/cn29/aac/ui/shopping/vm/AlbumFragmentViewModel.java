package com.cn29.aac.ui.shopping.vm;

import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.repo.itunes.Album;
import com.cn29.aac.repo.itunes.AlbumSearchResult;
import com.cn29.aac.repo.itunes.ItunesRepository;
import com.cn29.aac.repo.util.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Charles Ng on 20/10/2017.
 */

public class AlbumFragmentViewModel extends ViewModel {

  private ItunesRepository itunesRepository;


  public AlbumFragmentViewModel(ItunesRepository itunesRepository) {
    this.itunesRepository = itunesRepository;
  }

  public LiveData<ApiResponse<AlbumSearchResult>> getAlbumSearchResult(int artistId,
      String entity) {
    return itunesRepository.getAlbumSearchResult(artistId, entity);
  }

  public LiveData<Resource<List<Album>>> getAlbumResult(int artistId, String entity) {
    return itunesRepository.getAlbumResult(artistId, entity);
  }


}
