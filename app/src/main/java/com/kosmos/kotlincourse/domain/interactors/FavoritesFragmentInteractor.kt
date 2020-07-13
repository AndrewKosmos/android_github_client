package com.kosmos.kotlincourse.domain.interactors

import com.kosmos.kotlincourse.di.scopes.FragmentScope
import com.kosmos.kotlincourse.domain.repositories.FavoriteRepoRepository
import com.kosmos.kotlincourse.utils.SchedulersProvider
import javax.inject.Inject

@FragmentScope
class FavoritesFragmentInteractor @Inject constructor(
    private val favoritesRepository: FavoriteRepoRepository,
    private val schedulersProvider: SchedulersProvider
) {
    fun getAllFavorites() = favoritesRepository.getAll().subscribeOn(schedulersProvider.io())
    fun getAllFavoritesFlowable() = favoritesRepository.getAllAsFlowable().subscribeOn(schedulersProvider.io())
    fun getFavoriteRepo(fullname: String) = favoritesRepository.getRepository(fullname)
        .subscribeOn(schedulersProvider.io())
}