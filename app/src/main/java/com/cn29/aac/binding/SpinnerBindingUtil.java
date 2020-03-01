package com.cn29.aac.binding;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

/**
 * Created by Charles Ng on 7/9/2017.
 */

public class SpinnerBindingUtil {

  @BindingAdapter(value = {"selectedValue", "selectedValueAttrChanged"}, requireAll = false)
  public static void bindSpinnerData(AppCompatSpinner pAppCompatSpinner, String newSelectedValue, final InverseBindingListener newTextAttrChanged) {
    pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        newTextAttrChanged.onChange();
      }
      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });
    if (newSelectedValue != null) {
      int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
      pAppCompatSpinner.setSelection(pos, true);
    }
  }

  @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
  public static String captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
    return (String) pAppCompatSpinner.getSelectedItem();
  }

}