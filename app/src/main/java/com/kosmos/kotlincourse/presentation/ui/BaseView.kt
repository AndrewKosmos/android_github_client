package com.kosmos.kotlincourse.presentation.ui

interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showError(message : String)
}