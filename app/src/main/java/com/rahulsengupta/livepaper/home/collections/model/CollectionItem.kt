package com.rahulsengupta.livepaper.home.collections.model

data class CollectionItem(
    val id: Int,
    val coverPhoto: String,
    val coverPhotoWidth: Int,
    val coverPhotoHeight: Int,
    val totalPhotos: Int,
    val collectionName: String,
    val authorName: String,
    val authorImage: String
)
