package com.rahulsengupta.persistence.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.entity.LatestPhotoEntity
import com.rahulsengupta.persistence.entity.LatestPhotoRemoteKey

@Dao
interface LatestPhotosDao : BaseDao<LatestPhotoEntity> {

    @Query("DELETE FROM LatestPhotoEntity")
    suspend fun clearAll()

    @Query("SELECT * FROM LatestPhotoEntity")
    fun photos(): PagingSource<Int, LatestPhotoEntity>
}