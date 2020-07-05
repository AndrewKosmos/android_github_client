package com.kosmos.kotlincourse.di.modules

import com.kosmos.kotlincourse.di.components.MainComponent
import dagger.Module

@Module(subcomponents = [MainComponent::class])
class SubcomponentsModule {
}