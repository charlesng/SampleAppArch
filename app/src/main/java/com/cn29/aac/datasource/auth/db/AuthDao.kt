package com.cn29.aac.datasource.auth.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cn29.aac.repo.user.LoginBean

@Dao
interface AuthDao {
    @Query("SELECT * FROM Auth Where email = :email limit 1")
    fun getLogin(email: String?): LiveData<LoginBean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loginBean: LoginBean?): Long
}