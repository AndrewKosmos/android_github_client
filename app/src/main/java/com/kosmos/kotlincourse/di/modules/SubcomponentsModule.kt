package com.kosmos.kotlincourse.di.modules

import com.kosmos.kotlincourse.di.components.ExploreComponent
import com.kosmos.kotlincourse.di.components.MainComponent
import dagger.Module

@Module(subcomponents = [MainComponent::class, ExploreComponent::class])
class SubcomponentsModule {
}