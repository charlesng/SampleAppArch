package com.cn29.aac.ui.shopping.vm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.cn29.aac.repo.itunes.ItunesRepository;
import javax.inject.Inject;

/**
 * Created by Charles Ng on 20/10/2017.
 */

public class ArtistFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  private ItunesRepository itunesRepository;

  @Inject
  public ArtistFragmentViewModelFactory(ItunesRepository itunesRepository) {
    this.itunesRepository = itunesRepository;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new ArtistFragmentViewModel(itunesRepository);
  }
}
