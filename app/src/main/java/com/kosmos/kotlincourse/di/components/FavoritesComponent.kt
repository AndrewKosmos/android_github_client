package com.kosmos.kotlincourse.di.components

import com.kosmos.kotlincourse.di.modules.FavoritesModule
import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.presentation.ui.FavoritesFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FavoritesModule::class] )
interface FavoritesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(favoritesModule: FavoritesModule) : FavoritesComponent
    }

    fun inject(favoritesFragment: FavoritesFragment)

}