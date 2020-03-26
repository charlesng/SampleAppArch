package com.cn29.aac.ui.shopping

import android.content.SearchRecentSuggestionsProvider


class ArtistsSearchRecentSuggestionsProvider :
        SearchRecentSuggestionsProvider() {
    companion object {
        const val AUTHORITY = "com.cn29.aac.ArtistsSearchRecentSuggestionsProvider"
        const val MODE = DATABASE_MODE_QUERIES
    }

    init {
        setupSuggestions(AUTHORITY,
                         MODE)
    }
}