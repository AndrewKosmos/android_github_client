package com.kosmos.kotlincourse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kosmos.kotlincourse.data.database.dao.FavoriteRepositoryDao
import com.kosmos.kotlincourse.data.database.models.FavoriteRepositoryDbModel

@Database(entities = [FavoriteRepositoryDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteRepositoriesDao(): FavoriteRepositoryDao
}