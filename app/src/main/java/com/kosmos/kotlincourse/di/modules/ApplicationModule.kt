package com.kosmos.kotlincourse.di.modules

import android.content.Context
import com.kosmos.kotlincourse.utils.SchedulersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context,
                        private val scheduler: SchedulersProvider) {

    @Provides
    @Singleton
    fun provideApplicationContext() : Context = context

    @Provides
    @Singleton
    fun provideSheduler() : SchedulersProvider = scheduler

}