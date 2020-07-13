package com.kosmos.kotlincourse.domain.interactors

import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.repositories.FavoriteRepoRepository
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import com.kosmos.kotlincourse.utils.SchedulersProvider
import io.reactivex.Observable
import javax.inject.Inject

@FragmentScope
class ExploreFragmentInteractor @Inject constructor(private val gitResponseRepository: GitResponseRepository,
                                                    private val favoritesDbRepository: FavoriteRepoRepository,
                                                    private val schedulersProvider: SchedulersProvider) {
    fun getGitRepositories() = gitResponseRepository.getGitRepositoriesInfo().toObservable()
        .flatMap { repos -> Observable.fromIterable(repos) }
        .flatMap(
            { repo -> gitResponseRepository.getGitRepositiryInfo(repo.owner.username, repo.name).toObservable() },
            { repo, info -> GitRepository(name = repo.name, fullName = repo.fullName,
                description = repo.description, ownerLogin = repo.owner.username,
                ownerAvatarUrl = repo.owner.avatarUrl, language = info.language,
                starsCount = info.startCount, forksCount = info.forksCount) }
        )
        .subscribeOn(schedulersProvider.io())

    fun insertFavoriteRepository(repository: GitRepository) = favoritesDbRepository.insert(repository)
        .subscribeOn(schedulersProvider.io())
    fun deleteFavoriteRepository(repository: GitRepository) = favoritesDbRepository.delete(repository)
        .subscribeOn(schedulersProvider.io())
    fun isFavorite(fullname: String) = favoritesDbRepository.isFavorite(fullname)
        .subscribeOn(schedulersProvider.io())
}