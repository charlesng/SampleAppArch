package com.cn29.aac.datasource.github.remote

import androidx.room.TypeConverter
import androidx.room.util.StringUtil

object GithubTypeConverters {
    @TypeConverter
    fun stringToIntList(data: String?): List<Int>? {
        return if (data == null) {
            emptyList()
        } else StringUtil.splitToIntList(data)
    }

    @JvmStatic
    @TypeConverter
    fun intListToString(ints: List<Int?>?): String? {
        return StringUtil.joinIntoString(ints)
    }
}