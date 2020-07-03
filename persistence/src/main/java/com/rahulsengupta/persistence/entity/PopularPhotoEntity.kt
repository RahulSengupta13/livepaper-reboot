package com.rahulsengupta.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rahulsengupta.persistence.entity.common.Urls
import com.rahulsengupta.persistence.entity.common.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "PopularPhotoEntity")
data class PopularPhotoEntity(
    @PrimaryKey
    val id: String,
    val title: String?,
    val description: String?,
    val user: User?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    val width: Int,
    val height: Int,
    val likes: Int,
    val color: String,
    val urls: Urls?
)