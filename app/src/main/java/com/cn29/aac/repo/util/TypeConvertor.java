package com.cn29.aac.repo.util;

import android.arch.persistence.room.TypeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Charles Ng on 19/10/2017.
 */

public class TypeConvertor {

  private static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

  @TypeConverter
  public static String stringToIntList(Date date) {
    if (date != null) {
      return new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(date);
    }
    return new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(new Date());
  }

  @TypeConverter
  public static Date intListToString(String date) {
    if (!date.equals("")) {
      try {
        return new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).parse(date);
      } catch (ParseException e) {
        e.printStackTrace();
        return new Date();
      }
    }
    return new Date();
  }

}
