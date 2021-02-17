package com.rahulsengupta.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rahulsengupta.persistence.entity.common.CoverPhoto
import com.rahulsengupta.persistence.entity.common.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "CollectionEntity")
data class CollectionEntity(
    @PrimaryKey
    val id: String,
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