package com.kosmos.kotlincourse.domain.interactors

import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.repositories.FavoriteRepoRepository
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import com.kosmos.kotlincourse.utils.SchedulersProvider
import io.reactivex.Observable
import javax.inject.Inject

@FragmentScope
class RepositoryDetailInteractor @Inject constructor(private val gitResponseRepository: GitResponseRepository,
                                                     private val favoritesRepository: FavoriteRepoRepository,
                                                     private val schedulersProvider: SchedulersProvider) {
    fun getCommits(owner: String, name: String) = gitResponseRepository.getRepositoryCommits(owner, name)
        .toObservable()
        .flatMap { commits -> Observable.fromIterable(commits) }
        .take(10)
        .toList()
        .subscribeOn(schedulersProvider.io())

    fun isFavorite(fullname: String) = favoritesRepository.isFavorite(fullname)
        .subscribeOn(schedulersProvider.io())
    fun insertFavoriteRepository(repository: GitRepository) = favoritesRepository.insert(repository)
        .subscribeOn(schedulersProvider.io())
    fun deleteFavoriteRepository(repository: GitRepository) = favoritesRepository.delete(repository)
        .subscribeOn(schedulersProvider.io())
}