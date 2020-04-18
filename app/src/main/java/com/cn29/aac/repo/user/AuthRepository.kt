package com.cn29.aac.repo.user

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.cn29.aac.datasource.auth.db.AuthDao
import com.cn29.aac.datasource.auth.remote.FacebookAuth
import com.cn29.aac.datasource.auth.remote.GoogleAuth
import com.cn29.aac.datasource.auth.remote.MyCompanyAuth
import com.cn29.aac.util.Result
import com.cn29.aac.util.repositoryLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Charles Ng on 13/10/2017.
 */
@Singleton
class AuthRepository @Inject constructor(private val sharedPreferences: SharedPreferences,
                                         private val authDao: AuthDao,
                                         private val googleAuth: GoogleAuth,
                                         private val facebookAuth: FacebookAuth,
                                         private val myCompanyAuth: MyCompanyAuth,
                                         private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    fun login(mode: AuthMode,
              email: String,
              password: String): LiveData<Result<LoginBean>> = repositoryLiveData(
            localResult = { authDao.getLogin(email) },
            remoteResult = {
                when (mode) {
                    AuthMode.GOOGLE -> googleAuth.getLogin(email, password)
                    AuthMode.FACEBOOK -> facebookAuth.getLogin(email, password)
                    AuthMode.MYCOMPANY -> myCompanyAuth.getLogin(email,
                                                                 password)
                }
            },
            saveFetchResult = {
                authDao.insert(it)
                sharedPreferences.edit().putBoolean("isLogin", true).apply()
            },
            shouldFetch = {
                it == null || it.isLogin == 0
            },
            dispatcher = dispatcher
    )

    val isLogin: Boolean
        get() = sharedPreferences.getBoolean("isLogin", false)

    enum class AuthMode {
        GOOGLE, FACEBOOK, MYCOMPANY
    }

}