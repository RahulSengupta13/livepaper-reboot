package com.rahulsengupta.model.response

import com.rahulsengupta.model.response.common.Location
import com.rahulsengupta.model.response.common.Tag
import com.rahulsengupta.model.response.common.Urls
import com.rahulsengupta.model.response.common.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wallpaper(
    val id: String,
    @SerialName("updated_at")
    val updatedAt: String?,
    val downloads: Int,
    val likes: Int,
    val description: String?,
    val location: Location?,
    val tags: List<Tag>?,
    val urls: Urls,
    val user: User,
    val width: Int,
    val height: Int
)