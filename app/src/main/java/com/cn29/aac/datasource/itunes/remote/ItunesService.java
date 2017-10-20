package com.cn29.aac.datasource.itunes.remote;

import android.arch.lifecycle.LiveData;
import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.repo.itunes.AlbumSearchResult;
import com.cn29.aac.repo.itunes.ArtistSearchResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Charles Ng on 16/10/2017.
 */

public interface ItunesService {

  @GET("search")
  LiveData<ApiResponse<ArtistSearchResult>> getArtistSearchResult(@Query("term") String searchTerm);

  /*
  Look up all albums for Jack Johnson: https://itunes.apple.com/lookup?id=909253&entity=album.
   */
  @GET("lookup")
  LiveData<ApiResponse<AlbumSearchResult>> getAlbumSearchResult(@Query("id") int artistId,
      @Query("entity") String entity);

}
