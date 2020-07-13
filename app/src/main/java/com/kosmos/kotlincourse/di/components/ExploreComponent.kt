package com.kosmos.kotlincourse.di.components

import com.kosmos.kotlincourse.di.modules.ExploreModule
import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.presentation.ui.ExploreFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ExploreModule::class])
interface ExploreComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(exploreModule: ExploreModule) : ExploreComponent
    }

    fun inject(exploreFragment: ExploreFragment)

}