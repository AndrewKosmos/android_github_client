package com.kosmos.kotlincourse.presentation.presenters

import android.util.Log
import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.domain.interactors.LoginInteractor
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.domain.models.SigninResponse
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.utils.SchedulersProvider
import retrofit2.Response
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

@ActivityScope
class LoginPresenterImpl @Inject constructor(
    private val interactor: LoginInteractor,
    private val view: LoginPresenter.View,
    private val schedulersProvider: SchedulersProvider,
    private var sessionManager: SessionManager
) : LoginPresenter {

    override fun signIn(login: String, password: String) {
        view.showProgress()
        sessionManager.currentLogin = login
        sessionManager.currentPasswd = password
        interactor.getSignInResponse(login, password)
            .observeOn(schedulersProvider.ui())
            .subscribe(this::recievedSigninResponse, this::signinError)
    }

    override fun checkLoginStateFromPrefs() {
        if (sessionManager.isAlreadyLoggedIn()) {
            sessionManager.getSessionFromPrefs()
            view.moveToNextActivity()
        }
    }

    fun recievedSigninResponse(response: Response<SigninResponse>) {
        view.hideProgress()
        when (response.code()) {
            HttpsURLConnection.HTTP_OK -> {
                sessionManager.signIn()
                view.hideBadCredentialsError()
                view.moveToNextActivity()
            }
            HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                view.showBadCredentialsError()
            }
            else -> {
                Log.d("CotlinCourseApp", "recievedSigninResponse: $response")
            }
        }
    }

    fun signinError(throwable: Throwable) {
        view.hideProgress()
        Log.d("CotlinCourseApp", "signinError: ${throwable.message}")
    }
}