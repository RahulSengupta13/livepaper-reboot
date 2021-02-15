package com.rahulsengupta.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.rahulsengupta.model.response.Collection.*
import com.rahulsengupta.model.response.common.CoverPhoto
import com.rahulsengupta.model.response.common.User

@Serializable
data class CollectionDetails(
    val id: Int,
    val title: String,
    val description: String?,
    val updatedAt: String?,
    val curated: Boolean?,
    val featured: Boolean?,
    @SerialName("total_photos")
    val totalPhotos: Int?,
    @SerialName("cover_photo")
    val coverPhoto: CoverPhoto?,
    val user: User?
)