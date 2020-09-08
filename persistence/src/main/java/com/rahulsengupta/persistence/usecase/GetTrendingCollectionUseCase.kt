package com.rahulsengupta.persistence.usecase

import com.rahulsengupta.persistence.dao.TrendingCollectionDao
import com.rahulsengupta.persistence.entity.TrendingCollectionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTrendingCollectionUseCase {
    operator fun invoke(): Flow<List<TrendingCollectionEntity>?>
}

class GetTrendingCollectionUseCaseImpl @Inject constructor(
    private val dao: TrendingCollectionDao
) : GetTrendingCollectionUseCase {

    override fun invoke(): Flow<List<TrendingCollectionEntity>?> {
        return dao.getTrendingCollections()
    }
}