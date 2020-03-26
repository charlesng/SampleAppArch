package com.cn29.aac.repo.itunes

import androidx.room.Entity
import androidx.room.Index

/**
 * Created by Charles Ng on 20/10/2017.
 */
@Entity(indices = [Index("artistId")],
        primaryKeys = ["artistId", "collectionName"])
data class Album(var artistId: Long = 0,
                 var collectionName: String = "",
                 var collectionPrice: Double = 0.0,
                 var primaryGenreName: String? = null,
                 var releaseDate: String? = null,
                 var artworkUrl100: String? = null,
                 var collectionViewUrl: String? = null)