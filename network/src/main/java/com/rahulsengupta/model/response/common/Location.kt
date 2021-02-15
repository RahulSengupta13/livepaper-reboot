package com.rahulsengupta.model.response.common

import kotlinx.serialization.Serializable

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