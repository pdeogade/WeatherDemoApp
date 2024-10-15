package com.example.myweatherapp.domain.model

import com.example.myweatherapp.data.model.ConditionDto

data class Weather(
    val temperature: Int,
    val date: String,
    val wind: Int,
    val humidity: Int,
    val feelsLike: Int,
    val condition: ConditionDto,
    val uv: Int,
    val name: String,
    val forecasts: List<Forecast>
)
