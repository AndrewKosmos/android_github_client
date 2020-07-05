package com.kosmos.kotlincourse.presentation.presenters

import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.presentation.ui.BaseView

interface MainPresenter : BasePresenter {
    fun getGitRepositories()
}