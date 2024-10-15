package com.example.myweatherapp.data.model

import com.google.gson.annotations.SerializedName

data class NetworkForecastDto(
    @SerializedName("forecastday") val forecastday: List<NetworkForecastdayDto>
)
