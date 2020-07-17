package com.kosmos.kotlincourse.presentation.presenters

import android.util.Log
import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.domain.interactors.ExploreFragmentInteractor
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.utils.SchedulersProvider
import io.reactivex.functions.Consumer
import javax.inject.Inject

@FragmentScope
class ExploreFragmentPresenterImpl @Inject constructor(
    private val interactor: ExploreFragmentInteractor,
    private val view: ExploreFragmentPresenter.View,
    private val schedulersProvider: SchedulersProvider,
    private var sessionManager: SessionManager
) : ExploreFragmentPresenter {

    init {
        Log.d(TAG, "init explore presenter with user: ${sessionManager.currentLogin}")
    }

    private val repositoriesList: MutableList<GitRepository> = mutableListOf()

    override fun getGitRepositories() {
        repositoriesList.clear()
        view.showProgress()
        interactor.getGitRepositories().observeOn(schedulersProvider.ui())
            .subscribe( { repo: GitRepository? -> repo?.let { repositoriesList.add(it) } },
                this::gitResponseError, this::gitRepositoriesLoaded)
    }

    override fun refreshGitRepositories() {
        repositoriesList.clear()
        interactor.getGitRepositories().observeOn(schedulersProvider.ui())
            .subscribe( { repo: GitRepository? -> repo?.let { repositoriesList.add(it) } },
                this::gitResponseError, this::gitRepositoriesReloaded)
    }

    override fun repositoryLikeClicked(repository: GitRepository) {
        interactor.isFavorite(repository.fullName).observeOn(schedulersProvider.ui())
            .subscribe { value ->
                run {
                    if (value == 1) {
                        interactor.deleteFavoriteRepository(repository)
                            .observeOn(schedulersProvider.io())
                            .subscribe()
                        view.favoriteDeleted(repository)
                    } else {
                        interactor.insertFavoriteRepository(repository)
                            .observeOn(schedulersProvider.io())
                            .subscribe()
                        view.favoriteAdded(repository)
                    }
                }
            }
    }

    override fun onError(message: String) {
        Log.d(TAG, "onError: $message")
    }

    fun gitRepositoriesLoaded() {
        view.hideProgress()
        view.showGitRepositories(repositoriesList)
    }

    fun gitRepositoriesReloaded() {
        view.refreshGitRepositories(repositoriesList)
    }

    fun gitResponseError(throwable: Throwable) {
        Log.d(TAG, "gitResponseError: ${throwable.message}")
    }
}