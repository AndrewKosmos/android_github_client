package com.kosmos.kotlincourse.presentation.ui

import android.content.Intent
import android.net.http.HttpResponseCache
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
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
    private lateinit var loadingLayout: View
    private lateinit var errorTextView: TextView
    @Inject lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as CourseApplication).getLoginComponent(this).inject(this)

        loginEditText = findViewById(R.id.loginEditText)
        passwEditText = findViewById(R.id.passwdEditText)
        loginButton = findViewById(R.id.loginBtn)
        loadingLayout = findViewById(R.id.login_loading_layout)
        errorTextView = findViewById(R.id.login_error_tv)
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showBadCredentialsError() {
        errorTextView.visibility = View.VISIBLE
    }

    override fun hideBadCredentialsError() {
        errorTextView.visibility = View.GONE
    }

    override fun showProgress() {
        loadingLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadingLayout.visibility = View.GONE
    }

}