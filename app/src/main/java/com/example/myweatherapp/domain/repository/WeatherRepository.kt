package com.example.myweatherapp.domain.repository

import com.example.myweatherapp.domain.model.Weather
import com.example.myweatherapp.common.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecast(city: String): Flow<Result<Weather>>
}