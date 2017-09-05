package com.example.feedentry.repository.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Charles Ng on 5/9/2017.
 */
@Entity(tableName="feedEntrys")
public class FeedEntry {

  @PrimaryKey
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

  public final String id;
  public final String content;
  public final String details;

  public FeedEntry(String id, String content, String details) {
    this.id = id;
    this.content = content;
    this.details = details;
  }

  @Override
  public String toString() {
    return content;
  }
}
