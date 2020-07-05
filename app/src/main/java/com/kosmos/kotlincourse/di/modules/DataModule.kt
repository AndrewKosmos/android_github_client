package com.kosmos.kotlincourse.di.modules

import com.kosmos.kotlincourse.data.network.ApiService
import com.kosmos.kotlincourse.data.network.AuthInterceptor
import com.kosmos.kotlincourse.data.network.Constants
import com.kosmos.kotlincourse.data.repositories.GitResponseRepositoryImpl
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule(private val baseUrl : String) {

    @Provides
    @Singleton
    fun provideGitResponseRepository(repository: GitResponseRepositoryImpl) : GitResponseRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(AuthInterceptor(Constants.GIT_PRIVATE_TOKEN)).build())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

}