package com.cn29.aac.repo.github;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;


/**
 * Created by Charles Ng on 3/10/2017.
 */

@Entity(primaryKeys = "login")
public class User {

  @SerializedName("login")
  @NonNull
  public final String login;
  @SerializedName("avatar_url")
  public final String avatarUrl;
  @SerializedName("name")
  public final String name;
  @SerializedName("company")
  public final String company;
  @SerializedName("repos_url")
  public final String reposUrl;
  @SerializedName("blog")
  public final String blog;

  public User(String login, String avatarUrl, String name, String company,
      String reposUrl, String blog) {
    this.login = login;
    this.avatarUrl = avatarUrl;
    this.name = name;
    this.company = company;
    this.reposUrl = reposUrl;
    this.blog = blog;
  }
}