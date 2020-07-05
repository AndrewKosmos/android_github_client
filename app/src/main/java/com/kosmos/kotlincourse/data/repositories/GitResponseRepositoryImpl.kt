package com.kosmos.kotlincourse.data.repositories

import com.kosmos.kotlincourse.data.network.ApiService
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfo
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfoGlobal
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GitResponseRepositoryImpl @Inject constructor(private val serviceApi: ApiService)
    : GitResponseRepository {

    override fun getGitRepositoriesInfo(): Single<List<GitRepositoryInfoGlobal>> {
        return serviceApi.getRepositories()
    }

    override fun getGitRepositiryInfo(owner: String, repositoryName: String): Single<GitRepositoryInfo> {
        return serviceApi.getRepository(owner,repositoryName)
    }
}