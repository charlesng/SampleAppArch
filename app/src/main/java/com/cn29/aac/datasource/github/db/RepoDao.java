package com.cn29.aac.datasource.github.db;

import android.util.SparseIntArray;

import com.cn29.aac.repo.github.Contributor;
import com.cn29.aac.repo.github.Repo;
import com.cn29.aac.repo.github.RepoSearchResult;

import java.util.Collections;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;

/**
 * Created by Charles Ng on 9/10/2017.
 */

@Dao
public abstract class RepoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(Repo... repos);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insertContributors(List<Contributor> contributors);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insertRepos(List<Repo> repositories);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  public abstract long createRepoIfNotExists(Repo repo);

  @Query("SELECT * FROM repo WHERE owner_login = :login AND name = :name")
  public abstract LiveData<Repo> load(String login, String name);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT login, avatarUrl,repoName, repoOwner, contributions FROM contributor "
      + "WHERE repoName = :name AND repoOwner = :owner "
      + "ORDER BY contributions DESC")
  public abstract LiveData<List<Contributor>> loadContributors(String owner, String name);

  @Query("SELECT * FROM Repo "
      + "WHERE owner_login = :owner "
      + "ORDER BY stars DESC")
  public abstract LiveData<List<Repo>> loadRepositories(String owner);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(RepoSearchResult result);

  public LiveData<List<Repo>> loadOrdered(List<Integer> repoIds) {
    SparseIntArray order = new SparseIntArray();
    int index = 0;
    for (Integer repoId : repoIds) {
      order.put(repoId, index++);
    }
    return Transformations.map(loadById(repoIds), repositories -> {
      Collections.sort(repositories, (r1, r2) -> {
        int pos1 = order.get(r1.id);
        int pos2 = order.get(r2.id);
        return pos1 - pos2;
      });
      return repositories;
    });
  }

  @Query("SELECT * FROM Repo WHERE id in (:repoIds)")
  protected abstract LiveData<List<Repo>> loadById(List<Integer> repoIds);

}

