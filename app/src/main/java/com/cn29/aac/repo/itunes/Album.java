package com.cn29.aac.repo.itunes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

/**
 * Created by Charles Ng on 20/10/2017.
 */
@Entity(indices = {@Index("artistId")},
    primaryKeys = {"artistId", "collectionName"})
public class Album {

  @NonNull
  private int artistId;
  @NonNull
  private String collectionName = "";
  private double collectionPrice;
  private String primaryGenreName;

  private String releaseDate;

  private String artworkUrl100;

  private String collectionViewUrl;

  public int getArtistId() {
    return artistId;
  }

  public void setArtistId(int artistId) {
    this.artistId = artistId;
  }

  @NonNull
  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(@NonNull String collectionName) {
    this.collectionName = collectionName;
  }

  public double getCollectionPrice() {
    return collectionPrice;
  }

  public void setCollectionPrice(double collectionPrice) {
    this.collectionPrice = collectionPrice;
  }

  public String getPrimaryGenreName() {
    return primaryGenreName;
  }

  public void setPrimaryGenreName(String primaryGenreName) {
    this.primaryGenreName = primaryGenreName;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getCollectionViewUrl() {
    return collectionViewUrl;
  }

  public void setCollectionViewUrl(String collectionViewUrl) {
    this.collectionViewUrl = collectionViewUrl;
  }

  public String getArtworkUrl100() {
    return artworkUrl100;
  }

  public void setArtworkUrl100(String artworkUrl100) {
    this.artworkUrl100 = artworkUrl100;
  }
}
