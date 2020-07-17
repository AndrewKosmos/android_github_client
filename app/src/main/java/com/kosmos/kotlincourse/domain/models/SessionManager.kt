package com.kosmos.kotlincourse.domain.models

import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import javax.inject.Inject
import kotlin.math.log

class SessionManager @Inject constructor(private val context: Context) {

    var currentLogin: String? = null
    var currentPasswd: String? = null
    var isLoggedIn: Boolean = false

    fun signIn() {
        isLoggedIn = true
        val prefsRef = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefsRef.edit()
        editor.putString("user_login", currentLogin)
        editor.putString("user_passwd", currentPasswd)
        editor.putBoolean("isLogged", isLoggedIn)
        editor.apply()
    }

    fun signOut() {
        Log.d(TAG, "signOut: signout from user $currentLogin")
        isLoggedIn = false
        val prefsRef = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefsRef.edit()
        editor.remove("user_login")
        editor.remove("user_passwd")
        editor.putBoolean("isLogged", isLoggedIn)
        editor.apply()
    }

    fun isAlreadyLoggedIn() : Boolean {
        val prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        val isLoggedIn = prefs.getBoolean("isLogged",false)
        return isLoggedIn
    }

    fun getSessionFromPrefs() {
        val prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        val isLoggedIn = prefs.getBoolean("isLogged",false)
        val login = prefs.getString("user_login","")
        val passwd = prefs.getString("user_passwd","")
        if ( isLoggedIn && !login.isNullOrEmpty() && !passwd.isNullOrEmpty() ) {
            this.currentLogin = login
            this.currentPasswd = passwd
            this.isLoggedIn = isLoggedIn
        }
    }

}