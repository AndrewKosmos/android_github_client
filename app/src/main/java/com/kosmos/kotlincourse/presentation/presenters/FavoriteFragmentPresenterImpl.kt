package com.kosmos.kotlincourse.presentation.presenters

import android.util.Log
import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.domain.interactors.FavoritesFragmentInteractor
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.domain.utils.Constants
import com.kosmos.kotlincourse.utils.SchedulersProvider
import javax.inject.Inject

@FragmentScope
class FavoriteFragmentPresenterImpl @Inject constructor(
    private val interactor: FavoritesFragmentInteractor,
    private val view: FavoriteFragmentPresenter.View,
    private val schedulersProvider: SchedulersProvider,
    private var sessionManager: SessionManager
) : FavoriteFragmentPresenter {

    override fun getAllFavorites() {
        view.showProgress()
        interactor.getAllFavoritesFlowable().observeOn(schedulersProvider.ui())
            .subscribe(this::favoritesLoaded, this::loadError)
    }

    override fun onError(message: String) {
        Log.d(Constants.TAG, "onError: $message")
    }

    fun favoritesLoaded(favoritesList: List<GitRepository>) {
        view.hideProgress()
        view.showFavorites(favoritesList)
    }

    fun loadError(throwable: Throwable) {
        Log.d(Constants.TAG, "loadError: ${throwable.message}")
    }
}