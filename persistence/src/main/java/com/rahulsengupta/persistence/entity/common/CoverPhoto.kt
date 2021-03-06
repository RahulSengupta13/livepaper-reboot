package com.rahulsengupta.persistence.entity.common

import kotlinx.serialization.Serializable

@Serializable
data class CoverPhoto(
    val urls: Urls?,
    val width: Int,
    val height: Int
)