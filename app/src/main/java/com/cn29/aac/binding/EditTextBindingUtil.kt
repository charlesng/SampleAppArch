package com.cn29.aac.binding

import android.widget.EditText
import androidx.databinding.InverseMethod

/**
 * Created by Charles Ng on 7/9/2017.
 */
class EditTextBindingUtil {
    @InverseMethod("inverseText")
    fun bindText(editText: EditText,
                 newValue: String): String {
        editText.setText(newValue)
        return newValue
    }

    fun inverseText(editText: EditText): String {
        return editText.text.toString()
    }
}