package com.rahulsengupta.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CollectionRemoteKey")
data class CollectionRemoteKey(
    @PrimaryKey val id: Int,
    val previousKey: Int?,
    val nextKey: Int?
)
