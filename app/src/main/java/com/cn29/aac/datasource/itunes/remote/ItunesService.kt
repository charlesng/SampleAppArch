package com.cn29.aac.datasource.itunes.remote

import androidx.lifecycle.LiveData
import com.cn29.aac.datasource.api.ApiResponse
import com.cn29.aac.repo.itunes.AlbumSearchResult
import com.cn29.aac.repo.itunes.ArtistSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {
    @GET("search")
    fun getArtistSearchResult(@Query("term") searchTerm: String?): LiveData<ApiResponse<ArtistSearchResult?>?>?

    /*
  Look up all albums for Jack Johnson: https://itunes.apple.com/lookup?id=909253&entity=album.
   */
    @GET("lookup")
    fun getAlbumSearchResult(@Query("id") artistId: Int,
                             @Query("entity") entity: String?): LiveData<ApiResponse<AlbumSearchResult?>?>?
}