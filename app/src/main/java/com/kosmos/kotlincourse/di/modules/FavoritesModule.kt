package com.kosmos.kotlincourse.di.modules

import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.presentation.presenters.FavoriteFragmentPresenter
import com.kosmos.kotlincourse.presentation.presenters.FavoriteFragmentPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class FavoritesModule constructor(private val view: FavoriteFragmentPresenter.View) {

    @Provides
    @FragmentScope
    fun provideView() = view

    @Provides
    @FragmentScope
    fun providePresenter(presenterImpl: FavoriteFragmentPresenterImpl) : FavoriteFragmentPresenter = presenterImpl

}