package com.rahulsengupta.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rahulsengupta.network.BuildConfig
import com.rahulsengupta.network.service.UnsplashService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideNovelCovid19Service(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ) = provideService(
        UnsplashService.BASE_URL,
        okHttpClient,
        converterFactory,
        UnsplashService::class.java
    )

    @Provides
    fun provideUnSplashOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val headerAuthorizationInterceptor = Interceptor { chain ->
            val request = chain.request()
            val originalHttpUrl = request.url

            val url = originalHttpUrl
                .newBuilder()
                .build()

            val requestBuilder = request
                .newBuilder()
                .addHeader(
                    "Authorization",
                    "Client-ID ${BuildConfig.LivePaper_Unsplash_AccessKey}"
                )
                .url(url)
            chain.proceed(requestBuilder.build())
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerAuthorizationInterceptor)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    @Singleton
    fun provideMediaType() = "application/json".toMediaType()

    @Provides
    @Singleton
    fun provideConverterFactory(mediaType: MediaType): Converter.Factory =
        Json(JsonConfiguration(ignoreUnknownKeys = true, isLenient = true)).asConverterFactory(mediaType)

    private fun <T> provideService(
        baseUrl: String,
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory, clazz: Class<T>
    ): T {
        return createRetrofit(baseUrl, okhttpClient, converterFactory).create(clazz)
    }

    private fun createRetrofit(
        baseUrl: String,
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }
}