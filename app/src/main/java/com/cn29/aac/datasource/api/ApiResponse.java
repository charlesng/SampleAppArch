package com.cn29.aac.datasource.api;

/**
 * Created by Charles Ng on 3/10/2017.
 */

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import retrofit2.Response;

/**
 * Common class used by API responses.
 */
public class ApiResponse<T> {

  private static final Pattern LINK_PATTERN = Pattern
      .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"");
  private static final Pattern PAGE_PATTERN = Pattern.compile("page=(\\d)+");
  private static final String NEXT_LINK = "next";
  public final int code;
  @Nullable
  public final T body;
  @Nullable
  public final String errorMessage;
  @NonNull
  public final Map<String, String> links;

  public ApiResponse(Throwable error) {
    code = 500;
    body = null;
    errorMessage = error.getMessage();
    links = Collections.emptyMap();
  }

  public ApiResponse(Response<T> response) {
    code = response.code();
    if (response.isSuccessful()) {
      body = response.body();
      errorMessage = null;
    } else {
      String message = null;
      if (response.errorBody() != null) {
        try {
          message = response.errorBody().string();
        } catch (IOException ignored) {

        }
      }
      if (message == null || message.trim().length() == 0) {
        message = response.message();
      }
      errorMessage = message;
      body = null;
    }
    String linkHeader = response.headers().get("link");
    if (linkHeader == null) {
      links = Collections.emptyMap();
    } else {
      links = new ArrayMap<>();
      Matcher matcher = LINK_PATTERN.matcher(linkHeader);

      while (matcher.find()) {
        int count = matcher.groupCount();
        if (count == 2) {
          links.put(matcher.group(2), matcher.group(1));
        }
      }
    }
  }

  public boolean isSuccessful() {
    return code >= 200 && code < 300;
  }

  public Integer getNextPage() {
    String next = links.get(NEXT_LINK);
    if (next == null) {
      return null;
    }
    Matcher matcher = PAGE_PATTERN.matcher(next);
    if (!matcher.find() || matcher.groupCount() != 1) {
      return null;
    }
    try {
      return Integer.parseInt(matcher.group(1));
    } catch (NumberFormatException ex) {

      return null;
    }
  }
}