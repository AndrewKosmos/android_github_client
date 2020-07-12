package com.kosmos.kotlincourse.domain.repositories

import com.kosmos.kotlincourse.domain.models.Commit
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfo
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfoGlobal
import io.reactivex.Observable
import io.reactivex.Single

interface GitResponseRepository {

    fun getGitRepositoriesInfo() : Single<List<GitRepositoryInfoGlobal>>
    fun getGitRepositiryInfo(owner: String, repositoryName: String) : Single<GitRepositoryInfo>
    fun getRepositoryCommits(owner: String, repositoryName: String) : Single<List<Commit>>

}