package com.cn29.aac.datasource.github.remote

import androidx.room.TypeConverter

object GithubTypeConverters {
    @TypeConverter
    fun stringToIntList(data: String): List<Int> =
            data.split(",").filter { it.toIntOrNull() != null }
                    .map { it.toInt() }

    @JvmStatic
    @TypeConverter
    fun intListToString(ints: List<Int>): String =
            ints.joinToString(separator = ",")
}