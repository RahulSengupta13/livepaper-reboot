package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.entity.CollectionRemoteKey

@Dao
interface CollectionsRemoteKeyDao : BaseDao<CollectionRemoteKey> {

    @Query("SELECT * FROM CollectionRemoteKey WHERE id = :id")
    suspend fun remoteKeysById(id: Int): CollectionRemoteKey?

    @Query("DELETE FROM CollectionRemoteKey")
    suspend fun clearAll()
}