package com.cn29.aac.datasource.api;

/**
 * Created by Charles Ng on 3/10/2017.
 */

import android.arch.lifecycle.LiveData;
import com.cn29.aac.repo.github.Contributor;
import com.cn29.aac.repo.github.Repo;
import com.cn29.aac.repo.github.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * REST API access points
 */
public interface GithubService {

  @GET("users/{login}")
  LiveData<ApiResponse<User>> getUser(@Path("login") String login);

  @GET("users/{login}/repos")
  LiveData<ApiResponse<List<Repo>>> getRepos(@Path("login") String login);

  @GET("repos/{owner}/{name}")
  LiveData<ApiResponse<Repo>> getRepo(@Path("owner") String owner, @Path("name") String name);

  @GET("repos/{owner}/{name}/contributors")
  LiveData<ApiResponse<List<Contributor>>> getContributors(@Path("owner") String owner,
      @Path("name") String name);

  @GET("search/repositories")
  LiveData<ApiResponse<RepoSearchResponse>> searchRepos(@Query("q") String query);

  @GET("search/repositories")
  Call<RepoSearchResponse> searchRepos(@Query("q") String query, @Query("page") int page);
}