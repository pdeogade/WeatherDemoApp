package com.example.myweatherapp.data.model

import com.google.gson.annotations.SerializedName

data class NetworkForecastdayDto(
    @SerializedName("astro") val astro: AstroDto,
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Int,
    @SerializedName("day") val day: DayDto,
    @SerializedName("hour") val hour: List<NetworkHourDto>
)

