package com.cn29.aac.repo.github;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * Created by Charles Ng on 3/10/2017.
 */

@Entity(primaryKeys = {"repoName", "repoOwner", "login"},
    foreignKeys = @ForeignKey(entity = Repo.class,
        parentColumns = {"name", "owner_login"},
        childColumns = {"repoName", "repoOwner"},
        onUpdate = ForeignKey.CASCADE,
        deferred = true))
public class Contributor {

  @SerializedName("login")
  @NonNull
  private final String login;

  @SerializedName("contributions")
  private final int contributions;

  @SerializedName("avatar_url")
  private final String avatarUrl;

  @NonNull
  private String repoName;

  @NonNull
  private String repoOwner;

  public Contributor(String login, int contributions, String avatarUrl) {
    this.login = login;
    this.contributions = contributions;
    this.avatarUrl = avatarUrl;
  }

  public String getLogin() {
    return login;
  }

  public int getContributions() {
    return contributions;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public String getRepoName() {
    return repoName;
  }

  public void setRepoName(String repoName) {
    this.repoName = repoName;
  }

  public String getRepoOwner() {
    return repoOwner;
  }

  public void setRepoOwner(String repoOwner) {
    this.repoOwner = repoOwner;
  }
}