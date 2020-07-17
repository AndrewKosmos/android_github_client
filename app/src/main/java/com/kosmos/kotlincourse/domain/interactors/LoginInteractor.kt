package com.kosmos.kotlincourse.domain.interactors

import com.kosmos.kotlincourse.di.scopes.ActivityScope
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import com.kosmos.kotlincourse.utils.SchedulersProvider
import javax.inject.Inject

@ActivityScope
class LoginInteractor @Inject constructor(
    private val repository: GitResponseRepository,
    private val schedulersProvider: SchedulersProvider) {


    fun getSignInResponse(login: String, password: String) = repository.getSignInResponse(login,password)
        .subscribeOn(schedulersProvider.io())
}