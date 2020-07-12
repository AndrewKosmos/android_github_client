package com.kosmos.kotlincourse.domain.models

import android.os.Parcel
import android.os.Parcelable

data class GitRepository (
    val name: String,
    val fullName: String,
    val description: String?,
    val ownerLogin: String,
    val ownerAvatarUrl: String?,
    val language: String?,
    val starsCount: Int?,
    val forksCount: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(name)
        writeString(fullName)
        writeString(description)
        writeString(ownerLogin)
        writeString(ownerAvatarUrl)
        writeString(language)
        writeValue(starsCount)
        writeValue(forksCount)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<GitRepository> {
        override fun createFromParcel(parcel: Parcel) = GitRepository(parcel)

        override fun newArray(size: Int): Array<GitRepository?> = arrayOfNulls(size)
    }

}