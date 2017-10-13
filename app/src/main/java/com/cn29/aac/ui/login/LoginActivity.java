package com.cn29.aac.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityLoginBinding;
import com.cn29.aac.repo.user.AuthRepository.AuthMode;
import com.cn29.aac.repo.user.LoginBean;
import com.cn29.aac.ui.base.BaseAppCompatActivity;
import com.cn29.aac.ui.common.ActivityPermissionComponent;
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback;
import com.cn29.aac.ui.login.vm.LoginViewModel;
import com.cn29.aac.ui.main.AppArchNavigationDrawer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseAppCompatActivity {

  /**
   * Id to identity READ_CONTACTS permission request.
   */
  private static final int REQUEST_READ_CONTACTS = 0;
  /**
   * A dummy authentication store containing known user names and passwords.
   * TODO: remove after connecting to a real authentication system.
   */
  private static final String[] DUMMY_CREDENTIALS = new String[]{
      "foo@example.com:hello", "bar@example.com:world"
  };
  @Inject
  LoginViewModel loginViewModel;
  @Inject
  LoginBean loginBean;
  @Inject
  ActivityLoginBinding binding;
  @Inject
  ActivityPermissionComponent activityPermissionComponent;
  @Inject
  PermissionCallback permissionCallback;
  /**
   * Keep track of the login task to ensure we can cancel it if requested.
   */

  // UI references.
  private AutoCompleteTextView mEmailView;
  private EditText mPasswordView;
  private View mProgressView;
  private View mLoginFormView;

  private String email;
  private String password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
    if (loginViewModel.isLogin()) {
      showProgress(true);
      Single.timer(3, TimeUnit.SECONDS)
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(s -> {
            finish();
            startActivity(new Intent(LoginActivity.this, AppArchNavigationDrawer.class));
          });
    } else {
      loginView();
    }
  }

  private void loginView() {
    // Set up the login form.

    mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
      if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
        loginViewModel.login(AuthMode.MYCOMPANY, email, password)
            .observe(LoginActivity.this, loginBeanResource ->
            {
              assert loginBeanResource != null;
              switch (loginBeanResource.status) {
                case SUCCESS:
                  showProgress(false);
                  startActivity(new Intent(LoginActivity.this, AppArchNavigationDrawer.class));
                  break;
                case ERROR:
                  showProgress(false);
                  break;
                case LOADING:
                  showProgress(true);
                  break;
              }
            });
        return true;
      }
      return false;
    });

    Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
    mEmailSignInButton.setOnClickListener(view -> {
      email = mEmailView.getEditableText().toString();
      password = mEmailView.getEditableText().toString();
      loginViewModel.login(AuthMode.MYCOMPANY, email, password)
          .observe(LoginActivity.this, loginBeanResource ->
          {
            assert loginBeanResource != null;
            switch (loginBeanResource.status) {
              case SUCCESS:
                showProgress(false);
                startActivity(new Intent(LoginActivity.this, AppArchNavigationDrawer.class));
                break;
              case ERROR:
                showProgress(false);
                break;
              case LOADING:
                showProgress(true);
                break;
            }
          });
    });


  }

  private void init() {
    mEmailView = binding.email;
    mPasswordView = binding.password;
    mLoginFormView = binding.loginForm;
    mProgressView = binding.loginProgress;
    mEmailView.setText(loginBean.getEmail());
    mPasswordView.setText("HelloWorld");
  }


  private boolean isEmailValid(String email) {
    //TODO: Replace this with your own logic
    return email.contains("@");
  }

  private boolean isPasswordValid(String password) {
    //TODO: Replace this with your own logic
    return password.length() > 4;
  }

  /**
   * Shows the progress UI and hides the login form.
   */
  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  private void showProgress(final boolean show) {
    // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
    // for very easy animations. If available, use these APIs to fade-in
    // the progress spinner.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

      mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
      mLoginFormView.animate().setDuration(shortAnimTime).alpha(
          show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
      });

      mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
      mProgressView.animate().setDuration(shortAnimTime).alpha(
          show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
      });
    } else {
      // The ViewPropertyAnimator APIs are not available, so simply show
      // and hide the relevant UI components.
      mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
      mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
  }

}

