package com.cn29.aac.repo.github

import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["repoName", "repoOwner", "login"],
        foreignKeys = [ForeignKey(entity = Repo::class,
                                  parentColumns = ["name", "owner_login"],
                                  childColumns = ["repoName", "repoOwner"],
                                  onUpdate = ForeignKey.CASCADE,
                                  deferred = true)])
class Contributor(@field:SerializedName("login") val login: String,
                  @field:SerializedName("contributions") val contributions: Int,
                  @field:SerializedName("avatar_url") val avatarUrl: String,
                  @field:SerializedName("repoName") val repoName: String,
                  @field:SerializedName("repoOwner") val repoOwner: String) {

}