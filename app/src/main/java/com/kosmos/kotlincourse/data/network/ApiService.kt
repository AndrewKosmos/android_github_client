package com.kosmos.kotlincourse.data.network

import com.kosmos.kotlincourse.domain.models.Commit
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfo
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfoGlobal
import com.kosmos.kotlincourse.domain.models.SigninResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers("Accept: application/json")
    @GET("/")
    fun getSignInResponse(@Header("Authorization") basic: String): Single<Response<SigninResponse>>

    @GET("/repositories")
    fun getRepositories(): Single<List<GitRepositoryInfoGlobal>>

    @GET("/repos/{owner}/{repoName}")
    fun getRepository(@Path("owner") owner: String, @Path("repoName") name:String) : Single<GitRepositoryInfo>

    @GET("/repos/{owner}/{repoName}/commits")
    fun getCommits(@Path("owner") owner: String, @Path("repoName") name: String) : Single<List<Commit>>

}