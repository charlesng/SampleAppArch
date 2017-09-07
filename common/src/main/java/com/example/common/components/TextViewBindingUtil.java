package com.example.common.components;

import android.databinding.InverseMethod;
import android.widget.TextView;

/**
 * Created by Charles Ng on 7/9/2017.
 */

public class TextViewBindingUtil {

  @InverseMethod("inverseTextToInt")
  public String bindText(TextView textView, int newValue) {
    textView.setText(String.valueOf(newValue));
    return String.valueOf(newValue);
  }

  public int inverseTextToInt(TextView textView) {
    int value = Integer.valueOf(textView.getText().toString());
    return value;
  }

  @InverseMethod("inverseTextToDouble")
  public String bindText(TextView textView, Double newValue) {
    textView.setText(String.valueOf(newValue));
    return String.valueOf(newValue);
  }

  public Double inverseTextToDouble(TextView textView) {
    Double value = Double.valueOf(textView.getText().toString());
    return value;
  }
}
