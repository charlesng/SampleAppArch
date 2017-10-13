package com.cn29.aac.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * Created by Charles Ng on 7/9/2017.
 */

public class TextInputEditTextBindingUtil {


  @BindingAdapter({"validation", "errorMsg"})
  public static void setErrorEnable(TextInputLayout textInputLayout, StringRule stringRule,
      final String errorMsg) {
    textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      }

      @Override
      public void afterTextChanged(Editable editable) {
        textInputLayout
            .setErrorEnabled(stringRule.validate(textInputLayout.getEditText().getText()));
        if (stringRule.validate(textInputLayout.getEditText().getText())) {
          textInputLayout.setError(errorMsg);
        } else {
          textInputLayout.setError(null);
        }
      }
    });
    textInputLayout
        .setErrorEnabled(stringRule.validate(textInputLayout.getEditText().getText()));
    if (stringRule.validate(textInputLayout.getEditText().getText())) {
      textInputLayout.setError(errorMsg);
    } else {
      textInputLayout.setError(null);
    }
  }

  public interface StringRule {

    boolean validate(Editable s);
  }

  public static class Rule {

    public static StringRule NOT_EMPTY_RULE = s -> TextUtils.isEmpty(s.toString());
    public static StringRule EMAIL_RULE = s -> s.toString().length() > 18;
  }

}
