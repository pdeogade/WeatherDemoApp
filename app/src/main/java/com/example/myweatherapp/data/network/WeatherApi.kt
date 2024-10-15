package com.example.myweatherapp.data.network

import com.example.myweatherapp.BuildConfig
import com.example.myweatherapp.data.model.ForecastResponseDto
import com.example.myweatherapp.common.DEFAULT_WEATHER_DESTINATION
import com.example.myweatherapp.common.NUMBER_OF_DAYS
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") city: String = DEFAULT_WEATHER_DESTINATION,
        @Query("days") days: Int = NUMBER_OF_DAYS,
    ): ForecastResponseDto
}