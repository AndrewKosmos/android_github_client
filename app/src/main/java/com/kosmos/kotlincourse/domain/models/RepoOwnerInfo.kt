package com.kosmos.kotlincourse.domain.models

import com.google.gson.annotations.SerializedName

data class RepoOwnerInfo (
    @SerializedName("login")
    var username: String,
    @SerializedName("avatar_url")
    var avatarUrl: String
)