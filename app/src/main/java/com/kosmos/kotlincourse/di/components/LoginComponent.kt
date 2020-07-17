package com.kosmos.kotlincourse.di.components

import com.kosmos.kotlincourse.di.modules.LoginModule
import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.presentation.ui.LoginActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(loginModule: LoginModule) : LoginComponent
    }

    fun inject(loginActivity: LoginActivity)

}