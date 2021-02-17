package com.rahulsengupta.core.paging.home.collections

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rahulsengupta.datasource.UnSplashDataSource
import com.rahulsengupta.model.response.CollectionResponse
import com.rahulsengupta.persistence.LivePaperDatabase
import com.rahulsengupta.persistence.entity.CollectionRemoteKey
import com.rahulsengupta.persistence.entity.CollectionEntity
import com.rahulsengupta.persistence.entity.common.CoverPhoto
import com.rahulsengupta.persistence.entity.common.Urls
import com.rahulsengupta.persistence.entity.common.User
import com.rahulsengupta.persistence.entity.common.UserImage
import timber.log.Timber
import java.io.IOException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class CollectionsRemoteMediator constructor(
    private val database: LivePaperDatabase,
    private val dataSource: UnSplashDataSource
): RemoteMediator<Int, CollectionEntity>() {

    private val dao by lazy { database.collectionDao() }
    private val remoteKeyDao by lazy { database.collectionsRemoteKeyDao() }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, CollectionEntity>): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state) ?: throw InvalidObjectException("Remote key and the prevKey should not be null")
                    val previousKey = remoteKeys.previousKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                    previousKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                    nextKey
                }
            }

            val response = dataSource.getFeatureCollections(page, state.config.pageSize)
            val collections = response.data ?: return MediatorResult.Success(endOfPaginationReached = true)
            val collectionEntities = toCollectionEntities(collections)
            val endOfPaginationReached = collectionEntities.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.clearAll()
                    remoteKeyDao.clearAll()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = collectionEntities.map {
                    CollectionRemoteKey(id = it.id, previousKey = prevKey, nextKey = nextKey)
                }
                remoteKeyDao.insertAll(keys)
                dao.insertAll(collectionEntities)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            Timber.d("DB operation has failed : %s", exception.message)
            return MediatorResult.Error(exception)
        } catch (exception: Exception) {
            Timber.d("DB operation has failed : %s", exception.message)
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CollectionEntity>): CollectionRemoteKey? {
        val lastPage = state.pages.lastOrNull() { it.data.isNotEmpty() } ?: return null
        val lastMessage = lastPage.data.lastOrNull() ?: return null

        return remoteKeyDao.remoteKeysById(lastMessage.id)
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CollectionEntity>): CollectionRemoteKey? {
        val firstPage = state.pages.firstOrNull { it.data.isNotEmpty() } ?: return null
        val firstMessage = firstPage.data.firstOrNull() ?: return null

        return remoteKeyDao.remoteKeysById(firstMessage.id)
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CollectionEntity>): CollectionRemoteKey? {
        val anchorPosition = state.anchorPosition ?: return null
        val messageClosestToAnchorPosition =
            state.closestItemToPosition(anchorPosition) ?: return null

        return remoteKeyDao.remoteKeysById(messageClosestToAnchorPosition.id)
    }

    companion object {
        private const val DEFAULT_PAGE_INDEX = 1

        private fun toCollectionEntities(list: List<CollectionResponse>): List<CollectionEntity> {
            return list.map {
                CollectionEntity(
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
                        ),
                        width = it.coverPhoto.width,
                        height = it.coverPhoto.height
                    ),
                    publishedAt = it.publishedAt,
                    totalPhotos = it.totalPhotos
                )
            }
        }
    }
}