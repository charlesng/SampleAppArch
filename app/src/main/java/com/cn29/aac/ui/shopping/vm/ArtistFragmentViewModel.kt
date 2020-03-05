package com.cn29.aac.ui.shopping.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cn29.aac.datasource.api.ApiResponse
import com.cn29.aac.repo.itunes.Artist
import com.cn29.aac.repo.itunes.ArtistSearchResult
import com.cn29.aac.repo.itunes.ItunesRepository
import com.cn29.aac.repo.util.Resource

/**
 * Created by Charles Ng on 20/10/2017.
 */
class ArtistFragmentViewModel(private val itunesRepository: ItunesRepository) :
        ViewModel() {
    fun getArtistSearchResult(term: String?): LiveData<ApiResponse<ArtistSearchResult>> {
        return itunesRepository.getArtistSearchResult(term)
    }

    fun starArtist(artist: Artist?): Int {
        return itunesRepository.starArtist(artist)
    }

    fun getArtist(term: String?): LiveData<Resource<List<Artist>>> {
        return itunesRepository.getArtistResult(term)
    }

}