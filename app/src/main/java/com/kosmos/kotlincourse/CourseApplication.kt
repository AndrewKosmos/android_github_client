package com.kosmos.kotlincourse

import android.app.Application
import com.kosmos.kotlincourse.data.network.Constants
import com.kosmos.kotlincourse.di.components.ApplicationComponent
import com.kosmos.kotlincourse.di.components.DaggerApplicationComponent
import com.kosmos.kotlincourse.di.components.MainComponent
import com.kosmos.kotlincourse.di.modules.ApplicationModule
import com.kosmos.kotlincourse.di.modules.DataModule
import com.kosmos.kotlincourse.di.modules.MainModule
import com.kosmos.kotlincourse.presentation.ui.MainActivity
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
            .dataModule(DataModule(Constants.GITHUB_BASE_URL))
            .build()
    }

    fun getApplicationComponent() : ApplicationComponent = applicationComponent

    fun getMainComponent(mainActivity: MainActivity) : MainComponent = applicationComponent
        .getMainComponent().create(
            MainModule(
                mainActivity
            )
        )
}