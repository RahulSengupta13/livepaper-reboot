package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.entity.LatestPhotoRemoteKey

@Dao
interface LatestPhotoRemoteKeyDao : BaseDao<LatestPhotoRemoteKey> {

    @Query("SELECT * FROM LatestPhotoRemoteKey WHERE id = :id")
    suspend fun remoteKeysById(id: String): LatestPhotoRemoteKey?

    @Query("DELETE FROM LatestPhotoRemoteKey")
    suspend fun clearAll()
}