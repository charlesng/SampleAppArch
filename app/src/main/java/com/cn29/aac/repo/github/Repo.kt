package com.cn29.aac.repo.github

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index("id"), Index("owner_login")],
        primaryKeys = ["name", "owner_login"])
data class Repo(val id: Int,
                @JvmField @SerializedName("name") val name: String,
                @JvmField @SerializedName("full_name") val fullName: String,
                @JvmField @SerializedName("description") val description: String? = "",
                @JvmField @Embedded(prefix = "owner_") @field:SerializedName("owner") val owner: Owner,
                @JvmField @SerializedName("stargazers_count") val stars: Int) {

    data class Owner(@JvmField @field:SerializedName("login") val login: String,
                     @JvmField @SerializedName("url") val url: String?)
}