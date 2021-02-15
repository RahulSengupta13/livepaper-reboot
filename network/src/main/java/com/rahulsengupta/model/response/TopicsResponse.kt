package com.rahulsengupta.model.response

import com.rahulsengupta.model.response.common.CoverPhoto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicsResponse(
    val id: String,
    val title: String,
    val description: String,
    @SerialName("total_photos")
    val totalPhotos: String,
    @SerialName("cover_photo")
    val coverPhoto: CoverPhoto
)
