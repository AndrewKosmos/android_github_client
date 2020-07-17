package com.kosmos.kotlincourse.di.components

import android.content.Context
import com.kosmos.kotlincourse.data.database.AppDatabase
import com.kosmos.kotlincourse.data.network.ApiService
import com.kosmos.kotlincourse.di.modules.ApplicationModule
import com.kosmos.kotlincourse.di.modules.DataModule
import com.kosmos.kotlincourse.di.modules.SubcomponentsModule
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.domain.repositories.FavoriteRepoRepository
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import com.kosmos.kotlincourse.utils.SchedulersProvider
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class, SubcomponentsModule::class])
interface ApplicationComponent {
    fun getApplicationContext() : Context
    fun getScheduler() : SchedulersProvider
    fun getServiceApi() : ApiService
    fun getRetrofitInstance() : Retrofit
    fun getGitResponseRepository() : GitResponseRepository
    fun getMainComponent() : MainComponent.Factory
    fun getExploreComponent() : ExploreComponent.Factory
    fun getFavoritesComponent() : FavoritesComponent.Factory
    fun getRepoDetailComponent() : DetailsComponent.Factory
    fun getAppDatabase() : AppDatabase
    fun getFavoritesDbRepository() : FavoriteRepoRepository
    fun getSessionManager() : SessionManager
    fun getLoginComponent() : LoginComponent.Factory
}