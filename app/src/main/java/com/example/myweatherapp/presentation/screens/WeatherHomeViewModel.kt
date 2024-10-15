package com.example.myweatherapp.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.repository.WeatherRepository
import com.example.myweatherapp.presentation.widgets.SearchWidgetState
import com.example.myweatherapp.common.DEFAULT_WEATHER_DESTINATION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import com.example.myweatherapp.common.Result
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class WeatherHomeViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<WeatherHomeUiState> =
        MutableStateFlow(WeatherHomeUiState(isLoading = true))
    val uiState: StateFlow<WeatherHomeUiState> = _uiState.asStateFlow()

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    init {
        getWeather()
    }

    fun getWeather(city: String = DEFAULT_WEATHER_DESTINATION) {
        repository.getWeatherForecast(city).map { result ->
            when (result) {
                is Result.Success -> {
                    _uiState.value = WeatherHomeUiState(weather = result.data)
                }

                is Result.Error -> {
                    _uiState.value = WeatherHomeUiState(errorMessage = result.errorMessage)
                }

                Result.Loading -> {
                    _uiState.value = WeatherHomeUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}