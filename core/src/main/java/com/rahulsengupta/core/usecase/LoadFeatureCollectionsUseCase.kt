package com.rahulsengupta.core.usecase

import com.rahulsengupta.data.Result
import com.rahulsengupta.datasource.UnSplashDataSource
import com.rahulsengupta.model.response.FeaturedCollection
import com.rahulsengupta.persistence.dao.FeaturedCollectionDao
import com.rahulsengupta.persistence.entity.FeaturedCollectionEntity
import com.rahulsengupta.persistence.entity.common.CoverPhoto
import com.rahulsengupta.persistence.entity.common.Urls
import com.rahulsengupta.persistence.entity.common.User
import com.rahulsengupta.persistence.entity.common.UserImage
import javax.inject.Inject
import javax.inject.Singleton

interface LoadFeatureCollectionsUseCase {
    suspend operator fun invoke(page: Int, pageSize: Int)
}

@Singleton
class LoadFeatureCollectionsUseCaseImpl @Inject constructor(
    private val dataSource: UnSplashDataSource,
    private val dao: FeaturedCollectionDao
) : LoadFeatureCollectionsUseCase {

    override suspend fun invoke(page: Int, pageSize: Int) {
        val result = dataSource.getFeatureCollections(page, pageSize)
        if (result.status == Result.Status.ERROR) return

        val data = result.data ?: return
        val entityList = toEntityList(data)

        dao.insertAllOrReplace(entityList)
    }

    private fun toEntityList(list: List<FeaturedCollection>): List<FeaturedCollectionEntity> {
        return list.map {
            FeaturedCollectionEntity(
                id = it.id,
                title = it.title ?: "",
                description = it.description,
                user = User(
                    name = it.user?.name ?: "",
                    image = UserImage(
                        medium = it.user?.image?.medium,
                        large = it.user?.image?.large
                    ),
                    twitterUsername = it.user?.twitterUsername,
                    instagramUsername = it.user?.instagramUsername
                ),
                coverPhoto = CoverPhoto(
                    urls = Urls(
                        regular = it.coverPhoto.urls?.regular
                    )
                ),
                publishedAt = it.publishedAt,
                totalPhotos = it.totalPhotos
            )
        }
    }
}