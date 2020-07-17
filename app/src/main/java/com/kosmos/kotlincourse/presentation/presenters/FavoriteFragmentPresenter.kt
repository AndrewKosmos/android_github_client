package com.kosmos.kotlincourse.presentation.presenters

import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.presentation.ui.BaseView

interface FavoriteFragmentPresenter : BasePresenter {

    interface View : BaseView {
        fun showFavorites(favoritesList: List<GitRepository>)
        fun showEmptyState()
        fun hideEmptyState()
    }

    fun getAllFavorites()
}