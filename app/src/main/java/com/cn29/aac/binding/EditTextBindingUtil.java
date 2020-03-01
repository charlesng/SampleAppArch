package com.cn29.aac.binding;

import android.widget.EditText;

import androidx.databinding.InverseMethod;

/**
 * Created by Charles Ng on 7/9/2017.
 */

public class EditTextBindingUtil {
  @InverseMethod("inverseText")
  public String bindText(EditText editText, String newValue) {
    editText.setText(newValue);
    return newValue;
  }

  public String inverseText(EditText editText) {
    return editText.getText().toString();
  }
}
