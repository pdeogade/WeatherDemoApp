package com.example.myweatherapp.data.repository

import com.example.myweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.myweatherapp.common.Result
import com.example.myweatherapp.data.model.ConditionDto
import com.example.myweatherapp.domain.model.Forecast
import com.example.myweatherapp.domain.model.Hour
import com.example.myweatherapp.domain.model.Weather

object FakeWeatherRepository : WeatherRepository {
    override fun getWeatherForecast(city: String): Flow<Result<Weather>> {
        return flow {
            emit(Result.Loading)
            emit(Result.Success(fakeWeather))
        }
    }
}

val fakeWeather = Weather(
    temperature = 20,
    date = "2024-10-15",
    wind = 10,
    humidity = 60,
    feelsLike = 25,
    condition = ConditionDto(200, "sunny-icon", "Sunny"),
    uv = 5,
    name = "San Francisco",
    forecasts = listOf(
        Forecast(
            date = "2023-10-15",
            maxTemp = "25",
            minTemp = "15",
            sunrise = "06:30 AM",
            sunset = "06:30 PM",
            icon = "sunny-icon",
            hour = listOf(
                Hour("08:00 AM", "sunny-icon", "Sunny"),
                Hour("09:00 AM", "sunny-icon", "Sunny"),
                Hour("10:00 AM", "sunny-icon", "Sunny")
            )
        )
    )
)
