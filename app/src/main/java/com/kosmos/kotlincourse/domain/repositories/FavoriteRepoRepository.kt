package com.kosmos.kotlincourse.domain.repositories

import com.kosmos.kotlincourse.domain.models.GitRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface FavoriteRepoRepository {

    fun getAll() : Single<List<GitRepository>>
    fun getAllAsFlowable() : Flowable<List<GitRepository>>
    fun getRepository(fullName: String) : Single<GitRepository>
    fun isFavorite(fullName: String) : Single<Int>
    fun insert(repository: GitRepository) : Completable
    fun update(repository: GitRepository) : Completable
    fun delete(repository: GitRepository) : Completable

}