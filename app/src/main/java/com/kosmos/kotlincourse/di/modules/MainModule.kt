package com.kosmos.kotlincourse.di.modules

import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.presentation.presenters.MainPresenter
import com.kosmos.kotlincourse.presentation.presenters.MainPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule constructor(private val view: MainPresenter.MainView) {

    @Provides
    @ActivityScope
    fun provideMainView() = view

    @Provides
    @ActivityScope
    fun provideMainPresenter(presenterImpl: MainPresenterImpl) : MainPresenter = presenterImpl
}