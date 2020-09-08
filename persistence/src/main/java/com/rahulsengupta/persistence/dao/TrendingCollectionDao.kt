package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.entity.TrendingCollectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingCollectionDao : BaseDao<TrendingCollectionEntity> {

    @Query("SELECT * FROM TrendingCollectionEntity")
    fun getTrendingCollections(): Flow<List<TrendingCollectionEntity>?>
}