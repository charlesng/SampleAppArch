package com.cn29.aac.binding;

import android.databinding.InverseMethod;

/**
 * Created by Charles Ng on 8/9/2017. From @George Mount answer https://stackoverflow.com/questions/44769054/android-two-way-databinding-for-methods-with-parameters/44782542#44782542
 * The inverse method can also be in class scope , not only in static scope
 * ***Can be extended
 */

public class Converter {
  private static Converter converter;

  protected Converter() {
  }

  public static Converter getDefault() {
    if (converter == null) {
      converter = new Converter();
    }
    return converter;
  }

  @InverseMethod("fromDouble")
  public Double toDouble(String text) {
    return Double.valueOf(text);
  }



  public String fromDouble(Double value) {
    return String.valueOf(value);
  }

  @InverseMethod("fromInteger")
  public Integer toInteger(String text) {
    return Integer.valueOf(text);
  }


  public String fromInteger(Integer value) {
    return String.valueOf(value);
  }
}
