package com.cn29.aac.ui.shopping;

import com.cn29.aac.R;
import com.cn29.aac.databinding.FragmentArtistBinding;
import com.cn29.aac.ui.shopping.vm.ArtistFragmentViewModel;
import com.cn29.aac.ui.shopping.vm.ArtistFragmentViewModelFactory;
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 20/10/2017.
 */

@Module
public class ArtistFragmentModule {


  @Provides
  ArtistFragmentViewModel getArtistViewModel(ArtistFragmentViewModelFactory factory,
      ArtistFragment artistFragment) {
    return ViewModelProviders.of(artistFragment.getActivity(), factory)
        .get(ArtistFragmentViewModel.class);
  }

  @Provides
  ShoppingActivityViewModel getShoppingKartActivityViewModel(ArtistFragment artistFragment) {
    return ViewModelProviders.of(artistFragment.getActivity())
        .get(ShoppingActivityViewModel.class);
  }


  @Provides
  FragmentArtistBinding provideBinding(ArtistFragment artistFragment) {
    return DataBindingUtil
        .inflate(artistFragment.getActivity().getLayoutInflater(), R.layout.fragment_artist, null,
            false);
  }
}
