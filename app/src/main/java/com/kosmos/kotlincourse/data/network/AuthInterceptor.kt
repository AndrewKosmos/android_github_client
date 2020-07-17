package com.kosmos.kotlincourse.data.network

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class AuthInterceptor @Inject constructor(val token : String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "token $token")
        requestBuilder.addHeader("Accept", "application/json");
        return chain.proceed(requestBuilder.build())
    }
}

class BasicAuthInterceptor @Inject constructor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        val login = prefs.getString("user_login",null)
        val password = prefs.getString("user_passwd",null)
        val isLoggedIn = prefs.getBoolean("isLogged",false)
        val requestBuilder = chain.request().newBuilder()
        if ( isLoggedIn && !login.isNullOrEmpty() && !password.isNullOrEmpty() ) {
            val credentials = Base64.getEncoder()
                .encodeToString(("$login:$password").toByteArray())
            requestBuilder.addHeader("Authorization", "Basic $credentials")
            requestBuilder.addHeader("Accept", "application/json");
        }
        return chain.proceed(requestBuilder.build())
    }
}