package com.rahulsengupta.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rahulsengupta.persistence.converter.DbTypeConverters
import com.rahulsengupta.persistence.dao.CollectionsRemoteKeyDao
import com.rahulsengupta.persistence.dao.LatestPhotoRemoteKeyDao
import com.rahulsengupta.persistence.dao.LatestPhotosDao
import com.rahulsengupta.persistence.entity.CollectionRemoteKey
import com.rahulsengupta.persistence.entity.CollectionEntity
import com.rahulsengupta.persistence.entity.LatestPhotoEntity
import com.rahulsengupta.persistence.entity.LatestPhotoRemoteKey
import kotlinx.serialization.json.Json

object DatabaseMeta {
    const val NAME = "livepaper.db"
    const val VERSION = 1
}

@TypeConverters(value = [DbTypeConverters::class])
@Database(
    version = DatabaseMeta.VERSION,
    exportSchema = false,
    entities = [CollectionEntity::class, LatestPhotoEntity::class, LatestPhotoRemoteKey::class, CollectionRemoteKey::class]
)
abstract class LivePaperDatabase : RoomDatabase() {

    companion object {
        lateinit var json: Json
    }

    abstract fun latestPhotosDao(): LatestPhotosDao
    abstract fun latestPhotoRemoteKeyDao(): LatestPhotoRemoteKeyDao

    abstract fun collectionDao(): com.rahulsengupta.persistence.dao.CollectionDao
    abstract fun collectionsRemoteKeyDao(): CollectionsRemoteKeyDao
}