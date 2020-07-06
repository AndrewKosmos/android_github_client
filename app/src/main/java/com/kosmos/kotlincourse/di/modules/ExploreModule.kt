package com.kosmos.kotlincourse.di.modules

import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.presentation.presenters.ExploreFragmentPresenter
import com.kosmos.kotlincourse.presentation.presenters.ExploreFragmentPresenterImpl
import com.kosmos.kotlincourse.presentation.presenters.MainPresenter
import com.kosmos.kotlincourse.presentation.presenters.MainPresenterImpl
import com.kosmos.kotlincourse.presentation.ui.MainView
import dagger.Module
import dagger.Provides

@Module
class ExploreModule constructor(private val view: ExploreFragmentPresenter.View) {

    @Provides
    @FragmentScope
    fun provideView() = view

    @Provides
    @FragmentScope
    fun providePresenter(presenterImpl: ExploreFragmentPresenterImpl) : ExploreFragmentPresenter = presenterImpl

}