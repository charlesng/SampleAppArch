package com.cn29.aac.repo.feedentry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedEntrys")
data class FeedEntry(@ColumnInfo(name = "title") var title: String,
                     @ColumnInfo(name = "subtitle") var subTitle: String,
                     @ColumnInfo(name = "favourite") var isFavourite: Boolean = false,
                     @JvmField @ColumnInfo(name = "imageUrl") var imageUrl: String? = null,
                     @JvmField @PrimaryKey(autoGenerate = true) var uid: Int = 0)