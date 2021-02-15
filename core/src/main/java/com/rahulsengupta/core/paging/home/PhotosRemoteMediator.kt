package com.rahulsengupta.core.paging.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rahulsengupta.core.usecase.LoadPhotosUseCase.Companion.ORDER_BY_LATEST
import com.rahulsengupta.core.usecase.LoadPhotosUseCase.Companion.ORDER_BY_POPULAR
import com.rahulsengupta.datasource.UnSplashDataSource
import com.rahulsengupta.model.response.PhotoResponse
import com.rahulsengupta.persistence.LivePaperDatabase
import com.rahulsengupta.persistence.entity.LatestPhotoEntity
import com.rahulsengupta.persistence.entity.LatestPhotoRemoteKey
import com.rahulsengupta.persistence.entity.common.Urls
import com.rahulsengupta.persistence.entity.common.User
import com.rahulsengupta.persistence.entity.common.UserImage
import timber.log.Timber
import java.io.IOException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator constructor(
    private val database: LivePaperDatabase,
    private val dataSource: UnSplashDataSource
) : RemoteMediator<Int, LatestPhotoEntity>() {

    private val dao by lazy { database.latestPhotosDao() }
    private val remoteKeyDao by lazy { database.latestPhotoRemoteKeyDao() }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LatestPhotoEntity>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true) // Not supporting prepend for now.
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                    nextKey
                }
            }

            val response = dataSource.getPhotos(page, state.config.pageSize, ORDER_BY_LATEST)
            val photos = response.data ?: return MediatorResult.Success(endOfPaginationReached = true)
            val photoEntities = toLatestPhotoEntities(photos)
            val endOfPaginationReached = photoEntities.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.clearAll()
                    remoteKeyDao.clearAll()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = photoEntities.map {
                    LatestPhotoRemoteKey(id = it.id, previousKey = prevKey, nextKey = nextKey)
                }
                remoteKeyDao.insertAll(keys)
                dao.insertAll(photoEntities)
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

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, LatestPhotoEntity>): LatestPhotoRemoteKey? {
        val lastPage = state.pages.lastOrNull() { it.data.isNotEmpty() } ?: return null
        val lastMessage = lastPage.data.lastOrNull() ?: return null

        return remoteKeyDao.remoteKeysById(lastMessage.id)
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, LatestPhotoEntity>): LatestPhotoRemoteKey? {
        val firstPage = state.pages.firstOrNull { it.data.isNotEmpty() } ?: return null
        val firstMessage = firstPage.data.firstOrNull() ?: return null

        return remoteKeyDao.remoteKeysById(firstMessage.id)
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, LatestPhotoEntity>): LatestPhotoRemoteKey? {
        val anchorPosition = state.anchorPosition ?: return null
        val messageClosestToAnchorPosition =
            state.closestItemToPosition(anchorPosition) ?: return null

        return remoteKeyDao.remoteKeysById(messageClosestToAnchorPosition.id)
    }

    companion object {
        private const val DEFAULT_PAGE_INDEX = 1

        private fun toLatestPhotoEntities(photos: List<PhotoResponse>): List<LatestPhotoEntity> {
            return photos.map {
                LatestPhotoEntity(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    user = User(
                        name = it.user?.name,
                        image = UserImage(
                            medium = it.user?.image?.medium,
                            large = it.user?.image?.large,
                        ),
                        twitterUsername = it.user?.twitterUsername,
                        instagramUsername = it.user?.instagramUsername,
                    ),
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                    width = it.width,
                    height = it.height,
                    likes = it.likes,
                    color = it.color,
                    urls = Urls(
                        regular = it.urls?.regular,
                        small = it.urls?.small
                    )
                )
            }
        }
    }
}