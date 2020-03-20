package com.cn29.aac.datasource.auth.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cn29.aac.repo.user.LoginBean

@Database(entities = [LoginBean::class], version = 1)
abstract class AuthDb : RoomDatabase() {
    abstract fun authDao(): AuthDao
}