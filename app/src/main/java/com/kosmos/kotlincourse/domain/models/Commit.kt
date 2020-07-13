package com.kosmos.kotlincourse.domain.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Commit (
    var sha: String?,
    @SerializedName("commit")
    var commitDetail: CommitDetail?,
    @SerializedName("author")
    var author: Author?
)

data class CommitDetail(
    @SerializedName("author")
    var author: CommitDetailAuthor?,
    var message: String?
)

data class CommitDetailAuthor(
    var name: String?,
    var email: String?,
    var date: String?
)

data class Author (
    var login: String?,
    @SerializedName("avatar_url")
    var avatarUrl: String?
)