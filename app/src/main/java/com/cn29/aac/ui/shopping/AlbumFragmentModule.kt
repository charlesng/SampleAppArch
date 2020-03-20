package com.cn29.aac.ui.shopping

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.FragmentAlbumBinding
import com.cn29.aac.ui.shopping.vm.AlbumFragmentViewModel
import com.cn29.aac.ui.shopping.vm.AlbumFragmentViewModelFactory
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Charles Ng on 20/10/2017.
 */
@Module
class AlbumFragmentModule {
    @Provides
    fun getArtistViewModel(factory: AlbumFragmentViewModelFactory,
                           artistFragment: AlbumFragment): AlbumFragmentViewModel {
        return ViewModelProvider(artistFragment.requireActivity(), factory)
                .get(AlbumFragmentViewModel::class.java)
    }

    @Provides
    fun getShoppingKartActivityViewModel(albumFragment: AlbumFragment): ShoppingActivityViewModel {
        return ViewModelProvider(albumFragment.requireActivity())
                .get(ShoppingActivityViewModel::class.java)
    }

    @Provides
    fun provideBinding(artistFragment: AlbumFragment): FragmentAlbumBinding {
        return DataBindingUtil
                .inflate(artistFragment.requireActivity().layoutInflater,
                         R.layout.fragment_album,
                         null,
                         false)
    }
}