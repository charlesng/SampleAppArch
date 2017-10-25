package com.cn29.aac.repo.itunes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.support.annotation.NonNull;
import com.cn29.aac.repo.util.TypeConvertor;
import java.util.Date;

/**
 * Created by Charles Ng on 16/10/2017.
 */
@Entity(indices = {@Index("artistId")},
    primaryKeys = {"artistId", "artistName"})
@TypeConverters(TypeConvertor.class)
public class Artist implements android.os.Parcelable {

  public static final Creator<Artist> CREATOR = new Creator<Artist>() {
    @Override
    public Artist createFromParcel(Parcel source) {
      return new Artist(source);
    }

    @Override
    public Artist[] newArray(int size) {
      return new Artist[size];
    }
  };
  @NonNull
  private long artistId;
  @NonNull
  private String artistName;
  private String trackName;
  private String artworkUrl100;
  private double trackPrice;
  private boolean isFavourite = false;
  private Date createDate;

  public Artist() {
  }

  protected Artist(Parcel in) {
    this.artistId = in.readLong();
    this.artistName = in.readString();
    this.trackName = in.readString();
    this.artworkUrl100 = in.readString();
    this.trackPrice = in.readDouble();
    this.isFavourite = in.readByte() != 0;
    long tmpCreateDate = in.readLong();
    this.createDate = tmpCreateDate == -1 ? null : new Date(tmpCreateDate);
  }

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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(this.artistId);
    dest.writeString(this.artistName);
    dest.writeString(this.trackName);
    dest.writeString(this.artworkUrl100);
    dest.writeDouble(this.trackPrice);
    dest.writeByte(this.isFavourite ? (byte) 1 : (byte) 0);
    dest.writeLong(this.createDate != null ? this.createDate.getTime() : -1);
  }
}
