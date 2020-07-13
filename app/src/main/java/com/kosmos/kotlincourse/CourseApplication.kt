package com.kosmos.kotlincourse

import android.app.Application
import com.kosmos.kotlincourse.data.network.Constants
import com.kosmos.kotlincourse.di.components.*
import com.kosmos.kotlincourse.di.modules.*
import com.kosmos.kotlincourse.presentation.ui.ExploreFragment
import com.kosmos.kotlincourse.presentation.ui.FavoritesFragment
import com.kosmos.kotlincourse.presentation.ui.MainActivity
import com.kosmos.kotlincourse.presentation.ui.RepositoryDetailFragment
import com.kosmos.kotlincourse.utils.SchedulersProvider

class CourseApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(
                ApplicationModule(
                    this,
                    SchedulersProvider()
                )
            )
            .dataModule(DataModule(this,Constants.GITHUB_BASE_URL,"database"))
            .build()
    }

    fun getApplicationComponent() : ApplicationComponent = applicationComponent

    fun getMainComponent(mainActivity: MainActivity) : MainComponent = applicationComponent
        .getMainComponent().create(
            MainModule(
                mainActivity
            )
        )

    fun getExploreComponent(exploreFragment: ExploreFragment) : ExploreComponent = applicationComponent
        .getExploreComponent().create(
            ExploreModule(
                exploreFragment
            )
        )

    fun getRepoDetailsComponent(detailsFragment: RepositoryDetailFragment) : DetailsComponent = applicationComponent
        .getRepoDetailComponent().create(
            RepositoryDetailModule(detailsFragment)
        )

    fun getFavoritesComponent(favoritesFragment: FavoritesFragment) : FavoritesComponent = applicationComponent
        .getFavoritesComponent().create(
            FavoritesModule(
                favoritesFragment
            )
        )
}