package com.kosmos.kotlincourse.domain.models

import com.google.gson.annotations.SerializedName

data class SigninResponse(
    @SerializedName("current_user_url")
    var userUrl: String?,
    @SerializedName("message")
    var message: String?
)