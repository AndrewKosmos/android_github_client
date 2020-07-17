package com.kosmos.kotlincourse.domain.repositories

import com.kosmos.kotlincourse.domain.models.Commit
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfo
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfoGlobal
import com.kosmos.kotlincourse.domain.models.SigninResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface GitResponseRepository {

    fun getSignInResponse(login: String, password: String) : Single<Response<SigninResponse>>
    fun getGitRepositoriesInfo() : Single<List<GitRepositoryInfoGlobal>>
    fun getGitRepositiryInfo(owner: String, repositoryName: String) : Single<GitRepositoryInfo>
    fun getRepositoryCommits(owner: String, repositoryName: String) : Single<List<Commit>>

}