package com.rahulsengupta.model.response

import com.rahulsengupta.model.response.FeaturedCollection.CoverPhoto
import com.rahulsengupta.model.response.FeaturedCollection.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val id: String,
    val title: String?,
    val description: String?,
    val user: User?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    val width: Int,
    val height: Int,
    val likes: Int,
    val color: String,
    val urls: CoverPhoto.Urls?
)