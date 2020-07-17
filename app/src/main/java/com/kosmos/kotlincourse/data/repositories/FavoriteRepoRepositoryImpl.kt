package com.kosmos.kotlincourse.data.repositories

import com.kosmos.kotlincourse.data.database.dao.FavoriteRepositoryDao
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.repositories.FavoriteRepoRepository
import com.kosmos.kotlincourse.data.database.mappers.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteRepoRepositoryImpl @Inject constructor(
    private val dao : FavoriteRepositoryDao
) : FavoriteRepoRepository {
    override fun getAll(forUser: String): Single<List<GitRepository>> = dao.getAll(forUser).map { it.toDomainList() }

    override fun getAllAsFlowable(forUser: String): Flowable<List<GitRepository>> = dao.getAllAsFlowable(forUser)
        .map { it.toDomainList() }

    override fun isFavorite(fullName: String, forUser: String): Single<Int> = dao.isFavorite(fullName, forUser)

    override fun insert(repository: GitRepository, forUser: String): Completable =
        dao.insert(repository.toFavoriteRepositoryDbModel(forUser))

    override fun update(repository: GitRepository, forUser: String): Completable =
        dao.update(repository.toFavoriteRepositoryDbModel(forUser))

    override fun delete(repository: GitRepository, forUser: String): Completable =
        dao.delete(repository.toFavoriteRepositoryDbModel(forUser))
}