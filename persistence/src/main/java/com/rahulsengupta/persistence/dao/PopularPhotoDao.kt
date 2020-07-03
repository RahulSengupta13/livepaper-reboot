package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.entity.PopularPhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularPhotoDao : BaseDao<PopularPhotoEntity> {

    @Query("SELECT * FROM PopularPhotoEntity")
    fun getFeaturedCollections(): Flow<List<PopularPhotoEntity>?>

}