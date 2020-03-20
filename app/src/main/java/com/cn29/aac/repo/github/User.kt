package com.cn29.aac.repo.github

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Created by Charles Ng on 3/10/2017.
 */
@Entity(primaryKeys = ["login"])
data class User(@field:SerializedName("login") val login: String,
                @field:SerializedName("avatar_url") val avatarUrl: String,
                @field:SerializedName("name") val name: String,
                @field:SerializedName("company") val company: String,
                @field:SerializedName("repos_url") val reposUrl: String,
                @field:SerializedName("blog") val blog: String)