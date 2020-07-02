package com.rahulsengupta.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rahulsengupta.persistence.entity.MockEntity
import kotlinx.serialization.json.Json

object DatabaseMeta {
    const val NAME = "livepaper.db"
    const val VERSION = 1
}

@Database(
    version = DatabaseMeta.VERSION,
    exportSchema = false,
    entities = [MockEntity::class]
)
abstract class LivePaperDatabase : RoomDatabase() {

    companion object {
        lateinit var json: Json
    }
}