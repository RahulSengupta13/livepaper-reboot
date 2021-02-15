package com.rahulsengupta.network.service

import com.rahulsengupta.model.response.*
import com.rahulsengupta.model.response.Collection
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashService {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

    @GET("collections/featured")
    suspend fun getFeaturedCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<Collection>>

    @GET("collections")
    suspend fun getCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<Collection>>

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String
    ): Response<List<PhotoResponse>>

    @GET("/collections/{collectionId}")
    suspend fun getCollectionDetails(
        @Path("collectionId") collectionId: Int
    ): Response<CollectionDetails>

    @GET("/collections/{collectionId}/photos")
    suspend fun getCollectionPhotos(
        @Path("collectionId") collectionId: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<CollectionWallpaper>>

    @GET("/photos/{id}/")
    suspend fun getWallpaper(
        @Path("id") id: String
    ): Response<Wallpaper>

    @GET("/topics?order_by=featured")
    suspend fun getTopics(): Response<Void>
}