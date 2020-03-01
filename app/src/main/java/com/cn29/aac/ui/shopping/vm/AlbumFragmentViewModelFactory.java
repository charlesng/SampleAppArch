package com.cn29.aac.ui.shopping.vm;

import com.cn29.aac.repo.itunes.ItunesRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by Charles Ng on 20/10/2017.
 */

public class AlbumFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  private ItunesRepository itunesRepository;

  @Inject
  public AlbumFragmentViewModelFactory(ItunesRepository itunesRepository) {
    super();
    this.itunesRepository = itunesRepository;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) new AlbumFragmentViewModel(this.itunesRepository);
  }
}
