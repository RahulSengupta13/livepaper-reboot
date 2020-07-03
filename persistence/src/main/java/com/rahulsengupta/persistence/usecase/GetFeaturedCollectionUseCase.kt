package com.rahulsengupta.persistence.usecase

import com.rahulsengupta.persistence.dao.FeaturedCollectionDao
import com.rahulsengupta.persistence.entity.FeaturedCollectionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFeaturedCollectionUseCase {
    operator fun invoke(): Flow<List<FeaturedCollectionEntity>?>
}

class GetFeaturedCollectionUseCaseImpl @Inject constructor(
    private val dao: FeaturedCollectionDao
) : GetFeaturedCollectionUseCase {

    override fun invoke(): Flow<List<FeaturedCollectionEntity>?> {
        return dao.getFeaturedCollections()
    }
}