package com.cn29.aac.repo.itunes

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.TypeConverters
import com.cn29.aac.repo.util.TypeConvertor
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Charles Ng on 16/10/2017.
 */
@Entity(indices = [Index("artistId")],
        primaryKeys = ["artistId", "artistName"])
@TypeConverters(
        TypeConvertor::class)
@Parcelize
data class Artist(@JvmField var artistId: Long = 0,
                  @JvmField var artistName: String,
                  @JvmField var trackName: String? = null,
                  @JvmField var artworkUrl100: String? = null,
                  @JvmField var trackPrice: Double = 0.0,
                  @JvmField var isFavourite: Boolean = false,
                  @JvmField var createDate: Date? = null

) : Parcelable {
    constructor() : this(
            artistId = 0,
            artistName = "",
            trackName = null,
            artworkUrl100 = null,
            trackPrice = 0.0,
            isFavourite = false,
            createDate = null
    )
}