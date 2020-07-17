package com.kosmos.kotlincourse.presentation.presenters

import com.kosmos.kotlincourse.presentation.ui.BaseView

interface LoginPresenter : BasePresenter {

    interface View : BaseView {
        fun moveToNextActivity()
        fun showBadCredentialsError()
    }

    fun signIn(login: String, password: String)
    fun checkLoginStateFromPrefs()

}