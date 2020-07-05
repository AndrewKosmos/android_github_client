package com.kosmos.kotlincourse.domain.models

import com.google.gson.annotations.SerializedName

data class GitRepositoryInfoGlobal (
    @SerializedName("name")
    var name: String,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("owner")
    var owner: RepoOwnerInfo
)