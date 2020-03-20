package com.cn29.aac.binding

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Charles Ng on 7/9/2017.
 */
object TextInputEditTextBindingUtil {
    @JvmStatic
    @BindingAdapter("validation", "errorMsg")
    fun setErrorEnable(textInputLayout: TextInputLayout,
                       stringRule: StringRule,
                       errorMsg: String?) {
        textInputLayout.editText
                ?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence,
                                                   i: Int,
                                                   i1: Int,
                                                   i2: Int) {
                    }

                    override fun onTextChanged(charSequence: CharSequence,
                                               i: Int,
                                               i1: Int,
                                               i2: Int) {
                    }

                    override fun afterTextChanged(editable: Editable) {
                        textInputLayout.isErrorEnabled = stringRule.validate(
                                textInputLayout.editText
                                        ?.text)
                        if (stringRule.validate(textInputLayout.editText
                                                        ?.text)) {
                            textInputLayout.error = errorMsg
                        } else {
                            textInputLayout.error = null
                        }
                    }
                })
        textInputLayout.isErrorEnabled = stringRule.validate(textInputLayout.editText
                                                                     ?.text)
        if (stringRule.validate(textInputLayout.editText!!.text)) {
            textInputLayout.error = errorMsg
        } else {
            textInputLayout.error = null
        }
    }

    interface StringRule {
        fun validate(s: Editable?): Boolean
    }

    object Rule {
        @JvmField
        var NOT_EMPTY_RULE: StringRule = object : StringRule {
            override fun validate(s: Editable?): Boolean {
                return TextUtils.isEmpty(s.toString())
            }
        }
        var EMAIL_RULE: StringRule = object : StringRule {
            override fun validate(s: Editable?): Boolean {
                return s.toString().length > 18
            }
        }
    }
}