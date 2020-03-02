package com.cn29.aac.datasource.github.remote

import com.cn29.aac.repo.github.Repo
import com.google.gson.annotations.SerializedName
import java.util.*

class RepoSearchResponse {
    @SerializedName("total_count")
    var total = 0
    @SerializedName("items")
    var items: List<Repo>? = null
    var nextPage: Int? = null

    val repoIds: List<Int>
        get() {
            val repoIds: MutableList<Int> = ArrayList()
            for (repo in items!!) {
                repoIds.add(repo.id)
            }
            return repoIds
        }
}