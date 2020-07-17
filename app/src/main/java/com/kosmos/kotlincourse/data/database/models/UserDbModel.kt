package com.kosmos.kotlincourse.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "users")
data class UserDbModel(
    @NotNull @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_login") val login: String
)