package com.rahulsengupta.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CollectionWallpaper(
    val id: String,
    val urls: Urls
) {

    @Serializable
    data class Urls(
        val regular: String?
    )
}