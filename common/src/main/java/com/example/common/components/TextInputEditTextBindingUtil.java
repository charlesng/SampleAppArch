package com.example.common.components;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputEditText;

/**
 * Created by Charles Ng on 7/9/2017.
 */

public class TextInputEditTextBindingUtil {

//  @InverseMethod("inverseText")
//  public String bindText(TextInputEditText textInputEditText, String newValue) {
//    textInputEditText.setText(newValue);
//    return newValue;
//  }
//
//  public String inverseText(TextInputEditText textInputEditText) {
//    return textInputEditText.getText().toString();
//  }

  @BindingAdapter({"app:error"})
  public static void loadImage(TextInputEditText textInputEditText, String errorMsg) {
    textInputEditText.setError(errorMsg);
  }
}
