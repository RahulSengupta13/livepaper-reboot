package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.entity.FeaturedCollectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeaturedCollectionDao : BaseDao<FeaturedCollectionEntity> {

    @Query("SELECT * FROM FeaturedCollectionEntity")
    fun getFeaturedCollections(): Flow<List<FeaturedCollectionEntity>?>
}