package com.cn29.aac.repo.feedentry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedEntrys")
class FeedEntry(@field:ColumnInfo(name = "title") var title: String,
                @field:ColumnInfo(name = "subtitle") var subTitle: String) {
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @JvmField
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String? = null

    @ColumnInfo(name = "favourite")
    var isFavourite = false

    override fun toString(): String {
        return "FeedEntry{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", isFavourite=" + isFavourite +
                '}'
    }

}