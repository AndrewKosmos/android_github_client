package com.kosmos.kotlincourse.domain.models

import com.google.gson.annotations.SerializedName

data class GitRepositoryInfo(
    @SerializedName("language")
    var language: String,
    @SerializedName("stargazers_count")
    var startCount: Int,
    @SerializedName("forks_count")
    var forksCount: Int
)