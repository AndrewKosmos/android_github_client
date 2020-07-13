package com.kosmos.kotlincourse.presentation.presenters

import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.domain.interactors.MainInteractor
import com.kosmos.kotlincourse.utils.SchedulersProvider
import javax.inject.Inject

@ActivityScope
class MainPresenterImpl @Inject constructor(
    private val interactor: MainInteractor,
    private val view: MainPresenter.MainView,
    private val schedulersProvider: SchedulersProvider
    ) : MainPresenter {

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }
}