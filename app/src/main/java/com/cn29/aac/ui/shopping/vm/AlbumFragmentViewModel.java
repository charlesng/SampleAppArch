package com.cn29.aac.ui.shopping.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.cn29.aac.repo.itunes.Album;
import com.cn29.aac.repo.itunes.ItunesRepository;
import com.cn29.aac.repo.util.Resource;
import java.util.List;

/**
 * Created by Charles Ng on 20/10/2017.
 */

public class AlbumFragmentViewModel extends ViewModel {

  private ItunesRepository itunesRepository;


  public AlbumFragmentViewModel(ItunesRepository itunesRepository) {
    this.itunesRepository = itunesRepository;
  }

  public LiveData<Resource<List<Album>>> getAlbum(int artistId, String entity) {
    return itunesRepository.getAlbumResult(artistId, entity);
  }
}
