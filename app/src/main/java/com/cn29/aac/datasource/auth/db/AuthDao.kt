package com.cn29.aac.datasource.auth.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cn29.aac.repo.user.LoginBean

@Dao
interface AuthDao {
    @Query("SELECT * FROM Auth Where email = :email limit 1")
    suspend fun getLogin(email: String): LoginBean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(loginBean: LoginBean): Long
}