package com.kosmos.kotlincourse

import android.app.Application
import com.kosmos.kotlincourse.data.network.Constants
import com.kosmos.kotlincourse.di.components.*
import com.kosmos.kotlincourse.di.modules.*
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.presentation.ui.*
import com.kosmos.kotlincourse.utils.SchedulersProvider

class CourseApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent
    private lateinit var sessionManager: SessionManager

    override fun onCreate() {
        super.onCreate()
        sessionManager = SessionManager(this)

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

    fun getSessionManager() : SessionManager = sessionManager

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

    fun getLoginComponent(loginActivity: LoginActivity) : LoginComponent = applicationComponent
        .getLoginComponent().create(
            LoginModule(
                loginActivity
            )
        )
}