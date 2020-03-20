package com.cn29.aac.repo.itunes

/**
 * Created by Charles Ng on 20/10/2017.
 */
data class AlbumSearchResult(val resultCount: Int = 0,
                             var results: List<Album>? = null)