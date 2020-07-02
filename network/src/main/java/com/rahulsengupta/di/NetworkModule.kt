package com.rahulsengupta.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
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
                    "Client-ID a52411a039cc119b8612b89c68db9de1c2c2cb0028966615f88f037464ecece9"
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
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideMediaType() = "application/json".toMediaType()

    @Provides
    @Singleton
    fun provideConverterFactory(mediaType: MediaType): Converter.Factory =
        Json(JsonConfiguration(ignoreUnknownKeys = true, isLenient = true)).asConverterFactory(
            mediaType
        )

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