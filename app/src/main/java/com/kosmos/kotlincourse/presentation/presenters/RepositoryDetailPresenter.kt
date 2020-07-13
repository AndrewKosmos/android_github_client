package com.kosmos.kotlincourse.presentation.presenters

import com.kosmos.kotlincourse.domain.models.Commit
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.presentation.ui.BaseView

interface RepositoryDetailPresenter : BasePresenter {

    interface View : BaseView {
        fun showRepositoryInfo(repository: GitRepository)
        fun showCommits(commits: List<Commit>)
        fun showLikeViewState(liked: Boolean)
    }

    fun getCommits(owner: String, name: String)
    fun repositoryLikeClicked(repository: GitRepository)
}