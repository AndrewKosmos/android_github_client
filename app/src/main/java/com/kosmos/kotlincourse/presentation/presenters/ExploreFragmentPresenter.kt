package com.kosmos.kotlincourse.presentation.presenters

import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.presentation.ui.BaseView

interface ExploreFragmentPresenter : BasePresenter {

    interface View : BaseView {
        fun showGitRepositories(repositories: List<GitRepository>)
        fun refreshGitRepositories(repositories: List<GitRepository>)
        fun favoriteDeleted(repository: GitRepository)
        fun favoriteAdded(repository: GitRepository)
    }

    fun getGitRepositories()
    fun refreshGitRepositories()
    fun repositoryLikeClicked(repository: GitRepository)
}