package com.cn29.aac.repo.util

import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Charles Ng on 19/10/2017.
 */
object TypeConvertor {
    private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    @JvmStatic
    @TypeConverter
    fun stringToIntList(date: Date?): String {
        return if (date != null) {
            SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(date)
        } else SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(Date())
    }

    @JvmStatic
    @TypeConverter
    fun intListToString(date: String): Date {
        return if (date != "") {
            try {
                SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
                Date()
            }
        } else Date()
    }
}