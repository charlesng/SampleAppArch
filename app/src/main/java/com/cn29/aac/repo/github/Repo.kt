package com.cn29.aac.repo.github

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index("id"), Index("owner_login")],
        primaryKeys = ["name", "owner_login"])
class Repo(val id: Int,
           @JvmField @field:SerializedName("name") val name: String,
           @JvmField @field:SerializedName("full_name") val fullName: String,
           @JvmField @field:SerializedName("description") val description: String,
           @JvmField @field:Embedded(prefix = "owner_") @field:SerializedName("owner") val owner: Owner,
           @JvmField @field:SerializedName("stargazers_count") val stars: Int) {

    class Owner(@JvmField @field:SerializedName("login") val login: String,
                @field:SerializedName("url") val url: String?) {

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other == null || javaClass != other.javaClass) {
                return false
            }
            val owner = other as Owner
            if (login != owner.login) {
                return false
            }
            return if (url != null) url == owner.url else owner.url == null
        }

        override fun hashCode(): Int {
            var result = login.hashCode()
            result = 31 * result + (url?.hashCode() ?: 0)
            return result
        }

    }
}