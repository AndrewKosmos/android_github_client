package com.kosmos.kotlincourse.data.database.dao

import androidx.room.*
import com.kosmos.kotlincourse.data.database.models.FavoriteRepositoryDbModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface FavoriteRepositoryDao {

    @Query("select * from fav_repos where user_login=:forUSer")
    fun getAll(forUSer: String): Single<List<FavoriteRepositoryDbModel>>

    @Query("select * from fav_repos where user_login=:forUser")
    fun getAllAsFlowable(forUser: String): Flowable<List<FavoriteRepositoryDbModel>>

    @Query("select * from fav_repos where full_name = :fullname and user_login=:forUser")
    fun getFavoriteRepository(fullname: String, forUser: String): Single<FavoriteRepositoryDbModel>

    @Query("select exists (select 1 from fav_repos where full_name=:fullname and user_login=:forUser)")
    fun isFavorite(fullname: String, forUser: String) : Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositoryDbModel: FavoriteRepositoryDbModel) : Completable

    @Update
    fun update(repositoryDbModel: FavoriteRepositoryDbModel) : Completable

    @Delete
    fun delete(repositoryDbModel: FavoriteRepositoryDbModel) : Completable

}