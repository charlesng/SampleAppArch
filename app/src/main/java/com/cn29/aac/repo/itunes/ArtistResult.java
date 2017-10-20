package com.cn29.aac.repo.itunes;

import java.util.ArrayList;

/**
 * Created by Charles Ng on 16/10/2017.
 */

public class ArtistResult {

  private int resultCount;

  private ArrayList<Artist> results;

  public int getResultCount() {
    return resultCount;
  }

  public void setResultCount(int resultCount) {
    this.resultCount = resultCount;
  }

  public ArrayList<Artist> getResults() {
    return results;
  }

  public void setResults(ArrayList<Artist> results) {
    this.results = results;
  }
}
