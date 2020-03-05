package com.cn29.aac.ui.location.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cn29.aac.repo.itunes.Artist
import com.cn29.aac.repo.itunes.ItunesRepository
import com.cn29.aac.repo.location.LastLocationListener
import com.cn29.aac.repo.util.Resource

/**
 * Created by Charles Ng on 7/9/2017.
 */
class LastLocationViewModel(private val lastLocationListener: LastLocationListener,
                            private val itunesRepository: ItunesRepository) :
        ViewModel() {
    val lastKnowLocation: LastLocationListener
        get() = lastLocationListener

    fun getSearchResult(searchTerm: String?): LiveData<Resource<List<Artist>>> {
        return itunesRepository.getArtistResult(searchTerm)
    }

}