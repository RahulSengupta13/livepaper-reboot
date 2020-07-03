package com.rahulsengupta.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "FeaturedCollectionEntity")
data class FeaturedCollectionEntity(
    @PrimaryKey
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
) {

    @Serializable
    data class CoverPhoto(
        val urls: Urls?
    ) {

        @Serializable
        data class Urls(
            val regular: String?
        )
    }

    @Serializable
    data class User(
        val name: String?,
        @SerialName("profile_image")
        val image: UserImage?,
        @SerialName("twitter_username")
        val twitterUsername: String?,
        @SerialName("instagram_username")
        val instagramUsername: String?
    ) {

        @Serializable
        data class UserImage(
            val medium: String?,
            val large: String?
        )
    }
}