package com.kosmos.kotlincourse.domain.interactors

import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import com.kosmos.kotlincourse.utils.SchedulersProvider
import io.reactivex.Observable
import javax.inject.Inject

@ActivityScope
class MainInteractor @Inject constructor(private val schedulersProvider: SchedulersProvider) {
}