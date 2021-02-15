package com.rahulsengupta.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rahulsengupta.persistence.converter.DbTypeConverters
import com.rahulsengupta.persistence.dao.FeaturedCollectionDao
import com.rahulsengupta.persistence.dao.LatestPhotoRemoteKeyDao
import com.rahulsengupta.persistence.dao.LatestPhotosDao
import com.rahulsengupta.persistence.dao.TrendingCollectionDao
import com.rahulsengupta.persistence.entity.FeaturedCollectionEntity
import com.rahulsengupta.persistence.entity.LatestPhotoEntity
import com.rahulsengupta.persistence.entity.LatestPhotoRemoteKey
import com.rahulsengupta.persistence.entity.TrendingCollectionEntity
import kotlinx.serialization.json.Json

object DatabaseMeta {
    const val NAME = "livepaper.db"
    const val VERSION = 1
}

@TypeConverters(value = [DbTypeConverters::class])
@Database(
    version = DatabaseMeta.VERSION,
    exportSchema = false,
    entities = [FeaturedCollectionEntity::class, TrendingCollectionEntity::class, LatestPhotoEntity::class, LatestPhotoRemoteKey::class]
)
abstract class LivePaperDatabase : RoomDatabase() {

    companion object {
        lateinit var json: Json
    }

    abstract fun collectionEntityDao(): FeaturedCollectionDao
    abstract fun trendingCollectionEntityDao(): TrendingCollectionDao
    abstract fun latestPhotosDao(): LatestPhotosDao
    abstract fun latestPhotoRemoteKeyDao(): LatestPhotoRemoteKeyDao
}