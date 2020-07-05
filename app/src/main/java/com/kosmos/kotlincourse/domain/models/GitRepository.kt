package com.kosmos.kotlincourse.domain.models

data class GitRepository (
    val name: String?,
    val fullName: String?,
    val description: String?,
    val ownerLogin: String?,
    val ownerAvatarUrl: String?,
    val language: String?,
    val starsCount: Int?,
    val forksCount: Int?
)