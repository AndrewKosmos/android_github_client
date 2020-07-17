package com.kosmos.kotlincourse.presentation.ui

import android.content.Intent
import android.net.http.HttpResponseCache
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kosmos.kotlincourse.CourseApplication
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.data.network.ApiService
import com.kosmos.kotlincourse.data.repositories.GitResponseRepositoryImpl
import com.kosmos.kotlincourse.domain.models.SigninResponse
import com.kosmos.kotlincourse.domain.utils.Constants
import com.kosmos.kotlincourse.presentation.presenters.LoginPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class LoginActivity : AppCompatActivity(), LoginPresenter.View {

    private lateinit var loginEditText: EditText
    private lateinit var passwEditText: EditText
    private lateinit var loginButton: Button
    @Inject lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as CourseApplication).getLoginComponent(this).inject(this)

        loginEditText = findViewById(R.id.loginEditText)
        passwEditText = findViewById(R.id.passwdEditText)
        loginButton = findViewById(R.id.loginBtn)
        presenter.checkLoginStateFromPrefs()
        loginButton.setOnClickListener {

            val loginStr = loginEditText.text.toString()
            val passwdStr = passwEditText.text.toString()
            if (loginStr.isNotEmpty() && passwdStr.isNotEmpty()) {
                presenter.signIn(loginStr, passwdStr)
            }

        }
    }

    override fun moveToNextActivity() {
        Log.d("CotlinCourseApp", "moveToNextActivity: move")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showBadCredentialsError() {
        Toast.makeText(this, "Bad Credentials!", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }
}