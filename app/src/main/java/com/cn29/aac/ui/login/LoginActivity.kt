package com.cn29.aac.ui.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityLoginBinding
import com.cn29.aac.repo.user.AuthRepository.AuthMode
import com.cn29.aac.repo.user.LoginBean
import com.cn29.aac.ui.base.BaseAppCompatActivity
import com.cn29.aac.ui.common.ActivityPermissionComponent
import com.cn29.aac.ui.login.vm.LoginViewModel
import com.cn29.aac.ui.main.AppArchNavigationDrawer
import com.cn29.aac.util.Result
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginActivity : BaseAppCompatActivity(),
        OnConnectionFailedListener, View.OnClickListener {

    @Inject
    lateinit var loginViewModel: LoginViewModel


    @Inject
    lateinit var loginBean: LoginBean


    @Inject
    lateinit var binding: ActivityLoginBinding


    @Inject
    lateinit var activityPermissionComponent: ActivityPermissionComponent

    // UI references.
    private var mEmailView: AutoCompleteTextView? = null
    private var mPasswordView: EditText? = null
    private var mProgressView: View? = null
    private var mLoginFormView: View? = null
    private var email: String? = null
    private var password: String? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                                  this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
        initUI()
        if (loginViewModel.isLogin) {
            showProgress(true)
            Single.timer(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { _: Long? ->
                        finish()
                        startActivity(
                                Intent(this@LoginActivity,
                                       AppArchNavigationDrawer::class.java))
                    }
        } else {
            loginView()
        }
    }

    private fun loginView() {
        // Set up the login form.
        mPasswordView!!.setOnEditorActionListener { _: TextView?, id: Int, _: KeyEvent? ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                loginViewModel.login(AuthMode.MYCOMPANY, email!!, password!!)
                        .observe(
                                this@LoginActivity,
                                Observer { loginBeanResource: Result<LoginBean> ->
                                    when (loginBeanResource) {
                                        is Result.Success -> {
                                            showProgress(
                                                    false)
                                            startActivity(
                                                    Intent(this@LoginActivity,
                                                           AppArchNavigationDrawer::class.java))
                                        }
                                        is Result.Error -> {
                                            showProgress(false)
                                        }
                                        is Result.Loading -> {
                                            showProgress(true)
                                        }
                                    }
                                })
                return@setOnEditorActionListener true
            }
            false
        }
        val mEmailSignInButton = findViewById<Button>(
                R.id.email_sign_in_button)
        mEmailSignInButton.setOnClickListener { _: View? ->
            email = mEmailView!!.editableText.toString()
            password = mEmailView!!.editableText.toString()
            loginViewModel.login(AuthMode.MYCOMPANY, email!!, password!!)
                    .observe(this@LoginActivity,
                             Observer { loginBeanResource: Result<LoginBean> ->
                                 when (loginBeanResource) {
                                     is Result.Success -> {
                                         showProgress(false)
                                         finish()
                                         startActivity(Intent(
                                                 this@LoginActivity,
                                                 AppArchNavigationDrawer::class.java))
                                     }
                                     is Result.Error -> showProgress(
                                             false)
                                     is Result.Loading -> showProgress(
                                             true)
                                     else -> Unit
                                 }
                             })
        }
    }

    /*
  setup all the ui components first
   */
    private fun initUI() {
        mEmailView = binding.email
        mPasswordView = binding.password
        mLoginFormView = binding.loginForm
        mProgressView = binding.loginProgress
        val signInButton = binding.signInButton
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setOnClickListener(this)
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
        mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
        mLoginFormView!!.animate().setDuration(shortAnimTime.toLong())
                .alpha(if (show) 0f else 1f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })
        mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
        mProgressView!!.animate().setDuration(shortAnimTime.toLong())
                .alpha((if (show) 1f else 0f))
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {}
    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_in_button -> signIn()
        }
    }

    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(
                mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int,
                                         resultCode: Int,
                                         data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(
                    data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG,
              "handleSignInResult:" + result.isSuccess)
        if (result.isSuccess) {
            // Signed in successfully, show authenticated UI.
            val acct = result.signInAccount
            loginViewModel.login(AuthMode.GOOGLE, acct?.email!!, password!!)
                    .observe(this@LoginActivity,
                             Observer { loginBeanResource: Result<LoginBean> ->
                                 when (loginBeanResource) {
                                     is Result.Success -> {
                                         showProgress(false)
                                         finish()
                                         startActivity(Intent(this@LoginActivity,
                                                              AppArchNavigationDrawer::class.java))
                                     }
                                     is Result.Error -> showProgress(
                                             false)
                                     is Result.Loading -> showProgress(
                                             true)
                                     else -> Unit
                                 }
                             })
        }
    }

    companion object {
        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private const val REQUEST_READ_CONTACTS = 0

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf(
                "foo@example.com:hello", "bar@example.com:world"
        )
        private const val RC_SIGN_IN = 200
        private val TAG = LoginActivity::class.java.simpleName
    }
}