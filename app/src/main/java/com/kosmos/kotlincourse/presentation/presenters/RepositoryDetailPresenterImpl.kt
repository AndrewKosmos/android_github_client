package com.kosmos.kotlincourse.presentation.presenters

import android.util.Log
import com.kosmos.kotlincourse.domain.interactors.RepositoryDetailInteractor
import com.kosmos.kotlincourse.domain.models.Commit
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.utils.SchedulersProvider
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class RepositoryDetailPresenterImpl @Inject constructor(
    private val interactor: RepositoryDetailInteractor,
    private val view: RepositoryDetailPresenter.View,
    private val schedulersProvider: SchedulersProvider,
    private var sessionManager: SessionManager
) : RepositoryDetailPresenter {

    override fun getCommits(owner: String, name: String) {
        Log.d(TAG, "getCommits: loading")
        view.showProgress()
        interactor.getCommits(owner, name)
            .observeOn(schedulersProvider.ui())
            .subscribe(this::commitsLoaded, this::commitsLoadError)
    }

    override fun repositoryLikeClicked(repository: GitRepository) {
        interactor.isFavorite(repository.fullName).observeOn(schedulersProvider.ui())
            .subscribe { value ->
                run {
                    if (value == 1) {
                        view.showLikeViewState(false)
                        interactor.deleteFavoriteRepository(repository)
                            .observeOn(schedulersProvider.io())
                            .subscribe()
                    } else {
                        view.showLikeViewState(true)
                        interactor.insertFavoriteRepository(repository)
                            .observeOn(schedulersProvider.io())
                            .subscribe()
                    }
                }
            }
    }

    override fun onError(message: String) {
        Log.d(TAG, "onError: $message")
    }

    private fun commitsLoaded(commits: List<Commit>) {
        Log.d(TAG, "commitsLoaded: commits")
        view.hideProgress()
        view.showCommits(commits)
    }

    private fun commitsLoadError(throwable: Throwable) {
        view.hideProgress()
        Log.d(TAG, "commitsLoadError: ${throwable.message}")
    }
}