package com.cn29.aac.ui.shopping;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by Charles Ng on 23/10/2017.
 */

public class ArtistsSearchRecentSuggestionsProvider extends SearchRecentSuggestionsProvider {

  public final static String AUTHORITY = "com.cn29.aac.ArtistsSearchRecentSuggestionsProvider";
  public final static int MODE = DATABASE_MODE_QUERIES;

  public ArtistsSearchRecentSuggestionsProvider() {
    setupSuggestions(AUTHORITY, MODE);
  }
}
