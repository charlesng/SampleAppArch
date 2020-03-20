package com.cn29.aac.ui.shopping

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityArtistDetailBinding
import com.cn29.aac.repo.itunes.Artist
import com.cn29.aac.ui.shopping.vm.ArtistDetailActivityViewModel
import com.cn29.aac.ui.shopping.vm.ArtistDetailActivityViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Charles Ng on 23/10/2017.
 */
@Module
class ArtistDetailActivityModule {
    @Provides
    fun provideViewModel(factory: ArtistDetailActivityViewModelFactory,
                         activity: ArtistDetailActivity): ArtistDetailActivityViewModel {
        return ViewModelProvider(activity, factory)
                .get(
                        ArtistDetailActivityViewModel::class.java)
    }

    @Provides
    fun provideBinding(activity: ArtistDetailActivity): ActivityArtistDetailBinding {
        return DataBindingUtil.setContentView(activity,
                                              R.layout.activity_artist_detail)
    }

    @Provides
    fun provideArtist(activity: ArtistDetailActivity): Artist {
        return if (activity.intent.hasExtra("artist")) {
            activity.intent.getParcelableExtra("artist")
        } else {
            Artist()
        }
    }
}