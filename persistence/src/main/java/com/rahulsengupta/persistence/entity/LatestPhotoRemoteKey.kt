package com.rahulsengupta.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LatestPhotoRemoteKey")
data class LatestPhotoRemoteKey(
    @PrimaryKey val id: String,
    val previousKey: Int?,
    val nextKey: Int?
)
