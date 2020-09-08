package com.rahulsengupta.core.usecase

import com.rahulsengupta.data.Result
import com.rahulsengupta.datasource.UnSplashDataSource
import com.rahulsengupta.model.response.Collection
import com.rahulsengupta.persistence.dao.FeaturedCollectionDao
import com.rahulsengupta.persistence.dao.TrendingCollectionDao
import com.rahulsengupta.persistence.entity.FeaturedCollectionEntity
import com.rahulsengupta.persistence.entity.TrendingCollectionEntity
import com.rahulsengupta.persistence.entity.common.CoverPhoto
import com.rahulsengupta.persistence.entity.common.Urls
import com.rahulsengupta.persistence.entity.common.User
import com.rahulsengupta.persistence.entity.common.UserImage
import javax.inject.Inject
import javax.inject.Singleton

interface LoadTrendingCollectionsUseCase {
    suspend operator fun invoke(page: Int, pageSize: Int)
}

@Singleton
class LoadTrendingCollectionsUseCaseImpl @Inject constructor(
    private val dataSource: UnSplashDataSource,
    private val dao: TrendingCollectionDao
) : LoadTrendingCollectionsUseCase {

    override suspend fun invoke(page: Int, pageSize: Int) {
        val result = dataSource.getCollections(page, pageSize)
        if (result.status == Result.Status.ERROR) return

        val data = result.data ?: return
        val entityList = toEntityList(data)

        dao.insertAllOrReplace(entityList)
    }

    private fun toEntityList(list: List<Collection>): List<TrendingCollectionEntity> {
        return list.map {
            TrendingCollectionEntity(
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
                        regular = it.coverPhoto.urls?.regular,
                        small = it.coverPhoto.urls?.small
                    )
                ),
                publishedAt = it.publishedAt,
                totalPhotos = it.totalPhotos
            )
        }
    }
}