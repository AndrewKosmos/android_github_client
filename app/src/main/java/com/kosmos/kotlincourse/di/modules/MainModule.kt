package com.kosmos.kotlincourse.di.modules

import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.domain.interactors.MainInteractor
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import com.kosmos.kotlincourse.presentation.presenters.MainPresenter
import com.kosmos.kotlincourse.presentation.presenters.MainPresenterImpl
import com.kosmos.kotlincourse.presentation.ui.BaseView
import com.kosmos.kotlincourse.presentation.ui.MainActivity
import com.kosmos.kotlincourse.presentation.ui.MainView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class MainModule constructor(private val view: MainView) {

    @Provides
    @ActivityScope
    fun provideMainView() = view

    @Provides
    @ActivityScope
    fun provideMainPresenter(presenterImpl: MainPresenterImpl) : MainPresenter = presenterImpl
}