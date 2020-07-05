package com.kosmos.kotlincourse.presentation.ui

import com.kosmos.kotlincourse.domain.models.GitRepository

interface MainView : BaseView {
    fun showGitRepositories(repositories : List<GitRepository>)
}