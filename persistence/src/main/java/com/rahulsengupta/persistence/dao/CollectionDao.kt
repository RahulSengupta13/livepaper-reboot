package com.rahulsengupta.persistence.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.entity.CollectionEntity

@Dao
interface CollectionDao : BaseDao<CollectionEntity> {

    @Query("DELETE FROM CollectionEntity")
    suspend fun clearAll()

    @Query("SELECT * FROM CollectionEntity")
    fun collections(): PagingSource<Int, CollectionEntity>
}