package com.example.myweatherapp.data.model

import com.google.gson.annotations.SerializedName
import com.example.myweatherapp.domain.model.Forecast
import com.example.myweatherapp.domain.model.Hour
import com.example.myweatherapp.domain.model.Weather

data class ForecastResponseDto(
    @SerializedName("current") val current: CurrentDto,
    @SerializedName("forecast") val forecast: NetworkForecastDto,
    @SerializedName("location") val location: LocationDto
)

fun ForecastResponseDto.toWeather(): Weather = Weather(
    temperature = current.tempC.toInt(),
    date = forecast.forecastday[0].date,
    wind = current.windKph.toInt(),
    humidity = current.humidity,
    feelsLike = current.feelslikeC.toInt(),
    condition = current.condition,
    uv = current.uv.toInt(),
    name = location.name,
    forecasts = forecast.forecastday.map { networkForecastday ->
        networkForecastday.toWeatherForecast()
    }
)

fun NetworkForecastdayDto.toWeatherForecast(): Forecast = Forecast(
    date = date,
    maxTemp = day.maxtempC.toInt().toString(),
    minTemp = day.mintempC.toInt().toString(),
    sunrise = astro.sunrise,
    sunset = astro.sunset,
    icon = day.condition.icon,
    hour = hour.map { networkHour ->
        networkHour.toHour()
    }
)

fun NetworkHourDto.toHour(): Hour = Hour(
    time = time,
    icon = condition.icon,
    temperature = tempC.toInt().toString(),
)