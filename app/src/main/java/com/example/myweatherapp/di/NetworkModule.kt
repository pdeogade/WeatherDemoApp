package com.example.myweatherapp.di

import android.app.Application
import com.example.myweatherapp.BuildConfig
import com.example.myweatherapp.common.BASE_URL
import com.example.myweatherapp.common.ConnectionManager
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.myweatherapp.data.network.WeatherApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val cacheDir = File(application.cacheDir, "http_cache")
        return Cache(cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    fun provideNetworkInfo(application: Application): ConnectionManager {
        return ConnectionManager(application)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache, connectionManager: ConnectionManager): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (connectionManager.isNetworkConnected()) { // Add your network condition here
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                } else {
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                }
                chain.proceed(request)
            }

        return if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(httpLoggingInterceptor).build()
        } else {
            okHttpClient.build()
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit
        .create(WeatherApi::class.java)
}