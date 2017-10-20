package com.cn29.aac.repo.itunes;

import java.util.List;

/**
 * Created by Charles Ng on 20/10/2017.
 */

public class AlbumSearchResult {

  private int resultCount;

  private List<Album> results;

  public List<Album> getResults() {
    return results;
  }

  public void setResults(List<Album> results) {
    this.results = results;
  }
}
