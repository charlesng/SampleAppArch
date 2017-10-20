package com.cn29.aac.repo.itunes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import com.cn29.aac.repo.util.TypeConvertor;
import java.util.Date;

/**
 * Created by Charles Ng on 16/10/2017.
 */
@Entity(indices = {@Index("artistId")},
    primaryKeys = {"artistId", "artistName"})
@TypeConverters(TypeConvertor.class)
public class Artist {
  @NonNull
  private long artistId;

  @NonNull
  private String artistName;
  private String trackName;
  private String artworkUrl100;
  private double trackPrice;
  private boolean isFavourite = false;
  private Date createDate;


  public long getArtistId() {
    return artistId;
  }

  public void setArtistId(long artistId) {
    this.artistId = artistId;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public String getArtworkUrl100() {
    return artworkUrl100;
  }

  public void setArtworkUrl100(String artworkUrl100) {
    this.artworkUrl100 = artworkUrl100;
  }

  public String getTrackName() {
    return trackName;
  }

  public void setTrackName(String trackName) {
    this.trackName = trackName;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public boolean isFavourite() {
    return isFavourite;
  }

  public void setFavourite(boolean favourite) {
    isFavourite = favourite;
  }

  public double getTrackPrice() {
    return trackPrice;
  }

  public void setTrackPrice(double trackPrice) {
    this.trackPrice = trackPrice;
  }
}
