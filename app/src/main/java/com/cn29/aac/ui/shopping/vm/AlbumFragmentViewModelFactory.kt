package com.cn29.aac.ui.shopping.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.cn29.aac.repo.itunes.ItunesRepository
import javax.inject.Inject

/**
 * Created by Charles Ng on 20/10/2017.
 */
class AlbumFragmentViewModelFactory @Inject constructor(private val itunesRepository: ItunesRepository) :
        NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumFragmentViewModel(itunesRepository) as T
    }

}