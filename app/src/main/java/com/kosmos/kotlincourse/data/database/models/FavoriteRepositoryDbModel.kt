package com.kosmos.kotlincourse.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "fav_repos", primaryKeys = ["user_login","full_name"])
data class FavoriteRepositoryDbModel(
    @NotNull @ColumnInfo(name = "user_login") val user: String,
    @NotNull @ColumnInfo(name = "full_name") val fullName: String,
    @NotNull @ColumnInfo(name = "owner_login") val ownerLogin: String,
    @NotNull @ColumnInfo(name = "repository_name") val name: String,
    val description: String?,
    @ColumnInfo(name = "avatar_url") val ownerAvatarUrl: String?,
    @ColumnInfo(name = "lang") val language: String?,
    @ColumnInfo(name = "stars") val starsCount: Int?,
    @ColumnInfo(name = "forks") val forksCount: Int?
)