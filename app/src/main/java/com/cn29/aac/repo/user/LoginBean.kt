package com.cn29.aac.repo.user

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Created by Charles Ng on 13/10/2017.
 */
@Entity(tableName = "Auth", primaryKeys = ["email"])
data class LoginBean(@JvmField @SerializedName("email") var email: String,
                     @SerializedName("authType") var authType: String,
                     @JvmField @SerializedName("isLogin") var isLogin: Int = 0,
                     @JvmField var password: String? = null
)