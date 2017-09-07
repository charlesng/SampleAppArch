package com.example.common.components;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by Charles Ng on 7/9/2017.
 */

public class SpinnerBindingUtil {

  @BindingAdapter(value = {"bind:selectedValue", "bind:selectedValueAttrChanged"}, requireAll = false)
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
  @InverseBindingAdapter(attribute = "bind:selectedValue", event = "bind:selectedValueAttrChanged")
  public static String captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
    return (String) pAppCompatSpinner.getSelectedItem();
  }

}