package com.kosmos.kotlincourse.di.modules

import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.presentation.presenters.RepositoryDetailPresenter
import com.kosmos.kotlincourse.presentation.presenters.RepositoryDetailPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryDetailModule constructor(private val view: RepositoryDetailPresenter.View) {

    @Provides
    @FragmentScope
    fun provideView() = view

    @Provides
    @FragmentScope
    fun providePresenter(presenterImpl: RepositoryDetailPresenterImpl) : RepositoryDetailPresenter = presenterImpl
}