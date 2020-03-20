package com.cn29.aac.ui.shopping

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.FragmentArtistBinding
import com.cn29.aac.ui.shopping.vm.ArtistFragmentViewModel
import com.cn29.aac.ui.shopping.vm.ArtistFragmentViewModelFactory
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel
import dagger.Module
import dagger.Provides


@Module
class ArtistFragmentModule {
    @Provides
    fun getArtistViewModel(factory: ArtistFragmentViewModelFactory,
                           artistFragment: ArtistFragment): ArtistFragmentViewModel {
        return ViewModelProvider(artistFragment.requireActivity(), factory)
                .get(ArtistFragmentViewModel::class.java)
    }

    @Provides
    fun getShoppingKartActivityViewModel(artistFragment: ArtistFragment): ShoppingActivityViewModel {
        return ViewModelProvider(artistFragment.requireActivity())
                .get(ShoppingActivityViewModel::class.java)
    }

    @Provides
    fun provideBinding(artistFragment: ArtistFragment): FragmentArtistBinding {
        return DataBindingUtil
                .inflate(artistFragment.requireActivity().layoutInflater,
                         R.layout.fragment_artist,
                         null,
                         false)
    }
}