package com.kosmos.kotlincourse.di.components

import com.kosmos.kotlincourse.di.modules.MainModule
import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.presentation.ui.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(mainModule: MainModule) : MainComponent
    }

    fun inject(mainActivity: MainActivity)

}