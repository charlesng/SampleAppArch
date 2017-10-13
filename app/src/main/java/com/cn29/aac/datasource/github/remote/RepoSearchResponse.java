package com.cn29.aac.datasource.github.remote;

/**
 * Created by Charles Ng on 3/10/2017.
 */

import android.support.annotation.NonNull;
import com.cn29.aac.repo.github.Repo;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO to hold repo search responses. This is different from the Entity in the database because
 * we are keeping a search result in 1 row and denormalizing list of results into a single column.
 */
public class RepoSearchResponse {

  @SerializedName("total_count")
  private int total;
  @SerializedName("items")
  private List<Repo> items;
  private Integer nextPage;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<Repo> getItems() {
    return items;
  }

  public void setItems(List<Repo> items) {
    this.items = items;
  }

  public Integer getNextPage() {
    return nextPage;
  }

  public void setNextPage(Integer nextPage) {
    this.nextPage = nextPage;
  }

  @NonNull
  public List<Integer> getRepoIds() {
    List<Integer> repoIds = new ArrayList<>();
    for (Repo repo : items) {
      repoIds.add(repo.id);
    }
    return repoIds;
  }
}