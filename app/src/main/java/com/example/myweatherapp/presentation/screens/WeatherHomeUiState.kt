package com.example.myweatherapp.presentation.screens

import com.example.myweatherapp.domain.model.Weather

data class WeatherHomeUiState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)