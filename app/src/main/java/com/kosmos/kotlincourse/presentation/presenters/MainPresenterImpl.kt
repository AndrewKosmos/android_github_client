package com.kosmos.kotlincourse.presentation.presenters

import android.util.Log
import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.domain.interactors.MainInteractor
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.presentation.ui.MainView
import com.kosmos.kotlincourse.utils.SchedulersProvider
import javax.inject.Inject

@ActivityScope
class MainPresenterImpl @Inject constructor(
    private val interactor: MainInteractor,
    private val view: MainView,
    private val schedulersProvider: SchedulersProvider
    ) : MainPresenter {

    private val repositoriesList: MutableList<GitRepository> = mutableListOf()

    override fun getGitRepositories() {
        view.showProgress()
        interactor.getGitRepositories().observeOn(schedulersProvider.ui())
            .subscribe( { repo: GitRepository? -> repo?.let { repositoriesList.add(it) } },
            this::gitResponseError, this::gitRepositoriesLoaded)
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    fun gitRepositoriesLoaded() {
        view.hideProgress()
        view.showGitRepositories(repositoriesList)
    }

    fun gitResponseError(throwable: Throwable) {
        Log.d(TAG, "gitResponseError: ${throwable.message}")
    }
}