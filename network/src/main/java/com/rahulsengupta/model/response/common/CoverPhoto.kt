package com.rahulsengupta.model.response.common

import kotlinx.serialization.Serializable

@Serializable
data class CoverPhoto(
    val urls: Urls?
)