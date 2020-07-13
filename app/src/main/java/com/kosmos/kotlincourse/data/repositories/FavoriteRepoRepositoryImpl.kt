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
    override fun getAll(): Single<List<GitRepository>> = dao.getAll().map { it.toDomainList() }

    override fun getAllAsFlowable(): Flowable<List<GitRepository>> = dao.getAllAsFlowable()
        .map { it.toDomainList() }

    override fun getRepository(fullName: String): Single<GitRepository> = dao.getFavoriteRepository(fullName)
        .map { it.toGitRepositoryModel() }

    override fun isFavorite(fullName: String): Single<Int> = dao.isFavorite(fullName)

    override fun insert(repository: GitRepository) =
        dao.insert(repository.toFavoriteRepositoryDbModel())

    override fun update(repository: GitRepository) =
        dao.update(repository.toFavoriteRepositoryDbModel())

    override fun delete(repository: GitRepository) =
        dao.delete(repository.toFavoriteRepositoryDbModel())
}