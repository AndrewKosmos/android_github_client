package com.kosmos.kotlincourse.data.network

import com.kosmos.kotlincourse.domain.models.Commit
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfo
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfoGlobal
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/repositories")
    fun getRepositories(): Single<List<GitRepositoryInfoGlobal>>

    @GET("/repos/{owner}/{repoName}")
    fun getRepository(@Path("owner") owner: String, @Path("repoName") name:String) : Single<GitRepositoryInfo>

    @GET("/repos/{owner}/{repoName}/commits")
    fun getCommits(@Path("owner") owner: String, @Path("repoName") name: String) : Single<List<Commit>>

}