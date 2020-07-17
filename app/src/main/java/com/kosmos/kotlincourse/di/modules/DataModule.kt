package com.kosmos.kotlincourse.di.modules

import android.content.Context
import androidx.room.Room
import com.kosmos.kotlincourse.data.database.AppDatabase
import com.kosmos.kotlincourse.data.database.dao.FavoriteRepositoryDao
import com.kosmos.kotlincourse.data.network.ApiService
import com.kosmos.kotlincourse.data.network.AuthInterceptor
import com.kosmos.kotlincourse.data.network.BasicAuthInterceptor
import com.kosmos.kotlincourse.data.network.Constants
import com.kosmos.kotlincourse.data.repositories.FavoriteRepoRepositoryImpl
import com.kosmos.kotlincourse.data.repositories.GitResponseRepositoryImpl
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.domain.repositories.FavoriteRepoRepository
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule(
    private val context: Context,
    private val baseUrl : String,
    private val databaseName: String
) {

    @Provides
    @Singleton
    fun provideGitResponseRepository(repository: GitResponseRepositoryImpl) : GitResponseRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideSessionManager() : SessionManager = SessionManager(context)

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(OkHttpClient.Builder().addInterceptor(AuthInterceptor(Constants.GIT_PRIVATE_TOKEN)).build())
            .client(OkHttpClient.Builder().addInterceptor(BasicAuthInterceptor(context)).build())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase() : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()

    @Provides
    @Singleton
    fun provideFavoritesRepoDao(appDatabase: AppDatabase) : FavoriteRepositoryDao =
        appDatabase.favoriteRepositoriesDao()

    @Provides
    @Singleton
    fun provideFavoritesDbRepository(repositoryImpl: FavoriteRepoRepositoryImpl) : FavoriteRepoRepository
            = repositoryImpl
}