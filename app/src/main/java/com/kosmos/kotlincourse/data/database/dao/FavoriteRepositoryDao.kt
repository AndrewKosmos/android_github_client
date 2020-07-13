package com.kosmos.kotlincourse.data.database.dao

import androidx.room.*
import com.kosmos.kotlincourse.data.database.models.FavoriteRepositoryDbModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface FavoriteRepositoryDao {

    @Query("select * from fav_repos")
    fun getAll(): Single<List<FavoriteRepositoryDbModel>>

    @Query("select * from fav_repos")
    fun getAllAsFlowable(): Flowable<List<FavoriteRepositoryDbModel>>

    @Query("select * from fav_repos where full_name = :fullname")
    fun getFavoriteRepository(fullname: String): Single<FavoriteRepositoryDbModel>

    @Query("select exists (select 1 from fav_repos where full_name=:fullname)")
    fun isFavorite(fullname: String) : Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositoryDbModel: FavoriteRepositoryDbModel) : Completable

    @Update
    fun update(repositoryDbModel: FavoriteRepositoryDbModel) : Completable

    @Delete
    fun delete(repositoryDbModel: FavoriteRepositoryDbModel) : Completable

}