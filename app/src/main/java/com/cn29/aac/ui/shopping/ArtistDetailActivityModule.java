package com.cn29.aac.ui.shopping;

import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityArtistDetailBinding;
import com.cn29.aac.repo.itunes.Artist;
import com.cn29.aac.ui.shopping.vm.ArtistDetailActivityViewModel;
import com.cn29.aac.ui.shopping.vm.ArtistDetailActivityViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 23/10/2017.
 */

@Module
public class ArtistDetailActivityModule {


  @Provides
  ArtistDetailActivityViewModel provideViewModel(ArtistDetailActivityViewModelFactory factory,
      ArtistDetailActivity activity) {
    return ViewModelProviders.of(activity, factory).get(ArtistDetailActivityViewModel.class);
  }

  @Provides
  ActivityArtistDetailBinding provideBinding(ArtistDetailActivity activity) {
    return DataBindingUtil.setContentView(activity, R.layout.activity_artist_detail);
  }

  @Provides
  Artist provideArtist(ArtistDetailActivity activity) {
    if (activity.getIntent().hasExtra("artist")) {
      return activity.getIntent().getParcelableExtra("artist");
    } else {
      return new Artist();
    }
  }
}
