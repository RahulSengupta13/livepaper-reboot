package com.rahulsengupta.model.response

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
    val user: Collection.User,
    val width: Int,
    val height: Int
) {

    @Serializable
    data class Urls(
        val regular: String?,
        val full: String?
    )

    @Serializable
    data class Tag(val title: String)

    @Serializable
    data class Location(
        val city: String?,
        val country: String?,
        val position: Position?
    ) {

        @Serializable
        data class Position(
            val latitude: String?,
            val longitude: String?
        )
    }
}