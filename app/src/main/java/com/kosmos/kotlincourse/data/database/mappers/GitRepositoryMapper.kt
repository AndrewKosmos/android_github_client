package com.kosmos.kotlincourse.data.database.mappers

import com.kosmos.kotlincourse.data.database.models.FavoriteRepositoryDbModel
import com.kosmos.kotlincourse.domain.models.GitRepository

fun GitRepository.toFavoriteRepositoryDbModel() = FavoriteRepositoryDbModel(this.fullName,
                                                                            this.ownerLogin,
                                                                            this.name,
                                                                            this.description,
                                                                            this.ownerAvatarUrl,
                                                                            this.language,
                                                                            this.starsCount,
                                                                            this.forksCount)

fun FavoriteRepositoryDbModel.toGitRepositoryModel() = GitRepository(this.name,
                                                                    this.fullName,
                                                                    this.description,
                                                                    this.ownerLogin,
                                                                    this.ownerAvatarUrl,
                                                                    this.language,
                                                                    this.starsCount,
                                                                    this.forksCount)

fun List<GitRepository>.toDbList() : List<FavoriteRepositoryDbModel> {
    val resultList: MutableList<FavoriteRepositoryDbModel> = mutableListOf()
    for (item in this) {
        resultList.add(item.toFavoriteRepositoryDbModel())
    }
    return resultList
}

fun List<FavoriteRepositoryDbModel>.toDomainList() : List<GitRepository> {
    val resultList: MutableList<GitRepository> = mutableListOf()
    for (item in this) {
        resultList.add(item.toGitRepositoryModel())
    }
    return resultList
}