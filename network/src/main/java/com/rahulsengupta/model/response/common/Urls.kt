package com.rahulsengupta.model.response.common

import kotlinx.serialization.Serializable

@Serializable
data class Urls(
    val regular: String?,
    val small: String?
)