package com.rahulsengupta.persistence.entity.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String?,
    @SerialName("profile_image")
    val image: UserImage?,
    @SerialName("twitter_username")
    val twitterUsername: String?,
    @SerialName("instagram_username")
    val instagramUsername: String?
)