package com.rahulsengupta.persistence.di

import android.app.Application
import androidx.room.Room
import com.rahulsengupta.persistence.DatabaseMeta
import com.rahulsengupta.persistence.LivePaperDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
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
    fun provideJson(): Json = Json {  }
}