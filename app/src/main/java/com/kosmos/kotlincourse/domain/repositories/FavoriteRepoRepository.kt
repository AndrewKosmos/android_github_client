package com.kosmos.kotlincourse.domain.repositories

import com.kosmos.kotlincourse.domain.models.GitRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface FavoriteRepoRepository {

    fun getAll(forUser: String) : Single<List<GitRepository>>
    fun getAllAsFlowable(forUser: String) : Flowable<List<GitRepository>>
    fun isFavorite(fullName: String, forUser: String) : Single<Int>
    fun insert(repository: GitRepository, forUser: String) : Completable
    fun update(repository: GitRepository, forUser: String) : Completable
    fun delete(repository: GitRepository, forUser: String) : Completable

}