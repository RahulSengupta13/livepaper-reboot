package com.rahulsengupta.persistence.dao

import androidx.room.*

interface BaseDao<T> {

    @Insert
    fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(item: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnore(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOrReplace(items: List<T>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllOrIgnore(items: List<T>): List<Long>

    @Update
    fun update(item: T): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrReplace(item: T): Int

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateOrIgnore(item: T): Int

    @Update
    fun updateAll(items: List<T>): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAllOrReplace(items: List<T>): Int

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateAllOrIgnore(items: List<T>): Int

    @Delete
    fun delete(item: T): Int

    @Delete
    fun deleteAll(items: List<T>): Int

}