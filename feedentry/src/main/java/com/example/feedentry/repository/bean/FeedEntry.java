package com.example.feedentry.repository.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Charles Ng on 5/9/2017.
 */
@Entity(tableName = "feedEntrys")
public class FeedEntry {

  @PrimaryKey(autoGenerate = true)
  private int uid;

  @ColumnInfo(name = "title")
  private String title;
  @ColumnInfo(name = "subtitle")
  private String subTitle;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubTitle() {
    return subTitle;
  }

  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }


  public FeedEntry(String title, String subTitle) {
    this.title = title;
    this.subTitle = subTitle;
  }

  @Override
  public String toString() {
    return "FeedEntry{" +
        "uid=" + uid +
        ", title='" + title + '\'' +
        ", subTitle='" + subTitle + '\'' +
        '}';
  }
}
