package com.rahulsengupta.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "MockEntity")
data class MockEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val list: Int
)