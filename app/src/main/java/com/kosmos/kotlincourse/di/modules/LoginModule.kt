package com.kosmos.kotlincourse.di.modules

import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.presentation.presenters.LoginPresenter
import com.kosmos.kotlincourse.presentation.presenters.LoginPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class LoginModule constructor(private val view: LoginPresenter.View) {

    @Provides
    @ActivityScope
    fun provideLoginView() = view

    @Provides
    @ActivityScope
    fun provideLoginPresenter(presenterImpl: LoginPresenterImpl) : LoginPresenter = presenterImpl

}