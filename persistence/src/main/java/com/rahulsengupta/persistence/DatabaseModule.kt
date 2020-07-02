package com.rahulsengupta.persistence

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, json: Json): LivePaperDatabase {
        return Room.databaseBuilder(
            application,
            LivePaperDatabase::class.java,
            DatabaseMeta.NAME
        ).fallbackToDestructiveMigration().build().apply {
            LivePaperDatabase.json = json
        }
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json(JsonConfiguration.Stable)

}