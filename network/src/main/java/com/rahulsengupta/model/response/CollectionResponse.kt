package com.rahulsengupta.model.response

import com.rahulsengupta.model.response.common.CoverPhoto
import com.rahulsengupta.model.response.common.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    val id: Int,
    val title: String?,
    val description: String?,
    val user: User?,
    @SerialName("cover_photo")
    val coverPhoto: CoverPhoto,
    @SerialName("published_at")
    val publishedAt: String?,
    @SerialName("total_photos")
    val totalPhotos: Int?
)