package com.rahulsengupta.persistence.entity.common

import kotlinx.serialization.Serializable

@Serializable
data class UserImage(
    val medium: String?,
    val large: String?
)