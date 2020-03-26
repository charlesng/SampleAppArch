package com.cn29.aac.ui.shopping.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cn29.aac.datasource.api.ApiResponse
import com.cn29.aac.repo.itunes.Album
import com.cn29.aac.repo.itunes.AlbumSearchResult
import com.cn29.aac.repo.itunes.ItunesRepository
import com.cn29.aac.repo.util.Resource

/**
 * Created by Charles Ng on 20/10/2017.
 */
class AlbumFragmentViewModel(private val itunesRepository: ItunesRepository) :
        ViewModel() {
    fun getAlbumSearchResult(artistId: Int,
                             entity: String?): LiveData<ApiResponse<AlbumSearchResult>> {
        return itunesRepository.getAlbumSearchResult(artistId, entity)
    }

    fun getAlbumResult(artistId: Int,
                       entity: String?): LiveData<Resource<List<Album>>> {
        return itunesRepository.getAlbumResult(artistId, entity)
    }

}