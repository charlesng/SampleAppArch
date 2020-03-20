package com.cn29.aac.ui.location.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.cn29.aac.repo.itunes.ItunesRepository
import com.cn29.aac.repo.location.LastLocationListener
import javax.inject.Inject

/**
 * Created by Charles Ng on 20/9/2017.
 */
class LastLocationViewModelFactory @Inject constructor(private val lastLocationListener: LastLocationListener,
                                                       private val itunesRepository: ItunesRepository) :
        NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LastLocationViewModel(lastLocationListener,
                                     itunesRepository) as T
    }

}