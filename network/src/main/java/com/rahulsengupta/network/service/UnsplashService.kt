package com.rahulsengupta.network.service

import com.rahulsengupta.model.response.Collection
import com.rahulsengupta.model.response.CollectionDetails
import com.rahulsengupta.model.response.CollectionWallpaper
import com.rahulsengupta.model.response.Wallpaper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashService {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

    @GET("collections")
    fun getCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<Collection>>

    @GET("/collections/{collectionId}")
    fun getCollectionDetails(
        @Path("collectionId") collectionId: Int
    ): Response<CollectionDetails>

    @GET("/collections/{collectionId}/photos")
    fun getCollectionPhotos(
        @Path("collectionId") collectionId: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<CollectionWallpaper>>

    @GET("/photos/{id}/")
    fun getWallpaper(
        @Path("id") id: String
    ): Response<Wallpaper>
}