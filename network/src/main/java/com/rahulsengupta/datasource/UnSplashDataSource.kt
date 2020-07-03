package com.rahulsengupta.datasource

import com.rahulsengupta.base.BaseDataSource
import com.rahulsengupta.network.service.UnsplashService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnSplashDataSource @Inject constructor(
    private val service: UnsplashService
) : BaseDataSource() {

    suspend fun getFeatureCollections(page: Int, pageSize: Int) = getResult {
        service.getFeaturedCollections(page, pageSize)
    }
}