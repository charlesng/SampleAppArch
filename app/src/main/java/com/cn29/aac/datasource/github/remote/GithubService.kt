package com.cn29.aac.datasource.github.remote

import androidx.lifecycle.LiveData
import com.cn29.aac.datasource.api.ApiResponse
import com.cn29.aac.repo.github.Contributor
import com.cn29.aac.repo.github.Repo
import com.cn29.aac.repo.github.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("users/{login}")
    fun getUser(@Path("login") login: String?): LiveData<ApiResponse<User?>?>?

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String?): LiveData<ApiResponse<List<Repo?>?>?>?

    @GET("repos/{owner}/{name}")
    fun getRepo(@Path("owner") owner: String?, @Path("name") name: String?): LiveData<ApiResponse<Repo?>?>?

    @GET("repos/{owner}/{name}/contributors")
    fun getContributors(@Path("owner") owner: String?,
                        @Path("name") name: String?): LiveData<ApiResponse<List<Contributor?>?>?>?

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String?): LiveData<ApiResponse<RepoSearchResponse?>?>?

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String?, @Query("page") page: Int): Call<RepoSearchResponse?>?
}