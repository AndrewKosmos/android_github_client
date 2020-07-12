package com.kosmos.kotlincourse.di.components

import com.kosmos.kotlincourse.di.modules.RepositoryDetailModule
import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.presentation.ui.RepositoryDetailFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [RepositoryDetailModule::class])
interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(detailModule: RepositoryDetailModule) : DetailsComponent
    }

    fun inject(detailsFragment: RepositoryDetailFragment)

}