package com.cn29.aac.datasource.github.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cn29.aac.repo.github.Contributor
import com.cn29.aac.repo.github.Repo
import com.cn29.aac.repo.github.RepoSearchResult

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repos: Repo?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContributors(contributors: List<Contributor?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repositories: List<Repo?>?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createRepoIfNotExists(repo: Repo?): Long

    @Query("SELECT * FROM repo WHERE owner_login = :login AND name = :name")
    suspend fun load(login: String?,
                     name: String?): Repo

    @Query("SELECT login, avatarUrl,repoName, repoOwner, contributions FROM contributor "
                   + "WHERE repoName = :name AND repoOwner = :owner "
                   + "ORDER BY contributions DESC")
    fun loadContributors(owner: String?,
                         name: String?): LiveData<List<Contributor>>

    @Query("SELECT * FROM Repo "
                   + "WHERE owner_login = :owner "
                   + "ORDER BY stars DESC")
    suspend fun loadRepositories(owner: String?): List<Repo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: RepoSearchResult?)

    @Query("SELECT * FROM Repo WHERE id in (:repoIds)")
    fun loadById(repoIds: List<Int>): LiveData<List<Repo>>
}