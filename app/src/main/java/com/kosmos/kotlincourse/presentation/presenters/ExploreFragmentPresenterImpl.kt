package com.kosmos.kotlincourse.presentation.presenters

import android.util.Log
import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.domain.interactors.ExploreFragmentInteractor
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.utils.SchedulersProvider
import javax.inject.Inject

@FragmentScope
class ExploreFragmentPresenterImpl @Inject constructor(
    private val interactor: ExploreFragmentInteractor,
    private val view: ExploreFragmentPresenter.View,
    private val schedulersProvider: SchedulersProvider
) : ExploreFragmentPresenter {

    private val repositoriesList: MutableList<GitRepository> = mutableListOf()

    override fun getGitRepositories() {
        view.showProgress()
        interactor.getGitRepositories().observeOn(schedulersProvider.ui())
            .subscribe( { repo: GitRepository? -> repo?.let { repositoriesList.add(it) } },
                this::gitResponseError, this::gitRepositoriesLoaded)
    }

    override fun onError(message: String) {
        Log.d(TAG, "onError: $message")
    }

    fun gitRepositoriesLoaded() {
        view.hideProgress()
        view.showGitRepositories(repositoriesList)
    }

    fun gitResponseError(throwable: Throwable) {
        Log.d(TAG, "gitResponseError: ${throwable.message}")
    }
}