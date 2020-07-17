package com.kosmos.kotlincourse.presentation.presenters

import android.util.Log
import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.domain.interactors.MainInteractor
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.utils.SchedulersProvider
import javax.inject.Inject

@ActivityScope
class MainPresenterImpl @Inject constructor(
    private val interactor: MainInteractor,
    private val view: MainPresenter.MainView,
    private val schedulersProvider: SchedulersProvider,
    private var sessionManager: SessionManager
    ) : MainPresenter {
    override fun signOut() {
        sessionManager.signOut()
        view.moveToLoginScreen()
    }
}