package com.cn29.aac.ui.shopping;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import com.cn29.aac.R;
import com.cn29.aac.databinding.FragmentAlbumBinding;
import com.cn29.aac.ui.shopping.vm.AlbumFragmentViewModel;
import com.cn29.aac.ui.shopping.vm.AlbumFragmentViewModelFactory;
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles Ng on 20/10/2017.
 */

@Module
public class AlbumFragmentModule {


  @Provides
  AlbumFragmentViewModel getArtistViewModel(AlbumFragmentViewModelFactory factory,
      AlbumFragment artistFragment) {
    return ViewModelProviders.of(artistFragment.getActivity(), factory)
        .get(AlbumFragmentViewModel.class);
  }

  @Provides
  ShoppingActivityViewModel getShoppingKartActivityViewModel(AlbumFragment albumFragment) {
    return ViewModelProviders.of(albumFragment.getActivity())
        .get(ShoppingActivityViewModel.class);
  }


  @Provides
  FragmentAlbumBinding provideBinding(AlbumFragment artistFragment) {
    return DataBindingUtil
        .inflate(artistFragment.getActivity().getLayoutInflater(), R.layout.fragment_album, null,
            false);
  }
}
