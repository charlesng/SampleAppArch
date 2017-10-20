package com.cn29.aac.datasource.itunes.remote;

import android.arch.lifecycle.LiveData;
import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.repo.itunes.ArtistResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Charles Ng on 16/10/2017.
 */

public interface ItunesService {

  @GET("search")
  LiveData<ApiResponse<ArtistResult>> getArtist(@Query("term") String searchTerm);
}
