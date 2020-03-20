package com.cn29.aac.binding

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

/**
 * Created by Charles Ng on 7/9/2017.
 */
object SpinnerBindingUtil {
    @BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"],
                    requireAll = false)
    fun bindSpinnerData(pAppCompatSpinner: AppCompatSpinner,
                        newSelectedValue: String?,
                        newTextAttrChanged: InverseBindingListener) {
        pAppCompatSpinner.onItemSelectedListener = object :
                OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View,
                                        position: Int,
                                        id: Long) {
                newTextAttrChanged.onChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        if (newSelectedValue != null) {
            val pos = (pAppCompatSpinner.adapter as ArrayAdapter<String?>).getPosition(
                    newSelectedValue)
            pAppCompatSpinner.setSelection(pos, true)
        }
    }

    @InverseBindingAdapter(attribute = "selectedValue",
                           event = "selectedValueAttrChanged")
    fun captureSelectedValue(pAppCompatSpinner: AppCompatSpinner): String {
        return pAppCompatSpinner.selectedItem as String
    }
}