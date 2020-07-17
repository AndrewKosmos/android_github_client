package com.kosmos.kotlincourse.data.repositories

import android.util.Log
import com.kosmos.kotlincourse.data.network.ApiService
import com.kosmos.kotlincourse.domain.models.Commit
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfo
import com.kosmos.kotlincourse.domain.models.GitRepositoryInfoGlobal
import com.kosmos.kotlincourse.domain.models.SigninResponse
import com.kosmos.kotlincourse.domain.repositories.GitResponseRepository
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class GitResponseRepositoryImpl @Inject constructor(private val serviceApi: ApiService)
    : GitResponseRepository {
    override fun getSignInResponse(login: String, password: String): Single<Response<SigninResponse>> {
        val credentials = Base64.getEncoder()
            .encodeToString(("$login:$password").toByteArray())
        return serviceApi.getSignInResponse("Basic $credentials")
    }
    override fun getGitRepositoriesInfo(): Single<List<GitRepositoryInfoGlobal>> = serviceApi.getRepositories()
    override fun getGitRepositiryInfo(owner: String, repositoryName: String): Single<GitRepositoryInfo>
            = serviceApi.getRepository(owner,repositoryName)
    override fun getRepositoryCommits(owner: String, repositoryName: String): Single<List<Commit>>
            = serviceApi.getCommits(owner,repositoryName)
}