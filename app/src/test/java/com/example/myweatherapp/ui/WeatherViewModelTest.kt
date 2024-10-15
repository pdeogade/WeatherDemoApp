package com.example.myweatherapp.ui

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import com.example.myweatherapp.data.repository.FakeWeatherRepository
import com.example.myweatherapp.data.repository.fakeWeather
import com.example.myweatherapp.domain.repository.WeatherRepository
import com.example.myweatherapp.presentation.screens.WeatherHomeUiState
import com.example.myweatherapp.presentation.screens.WeatherHomeViewModel
import kamal.aishwarya.weather.ui.CoroutineDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

    private lateinit var viewModel: WeatherHomeViewModel

    private val weatherRepository: WeatherRepository = FakeWeatherRepository

    @Before
    fun setUp() {
        viewModel = WeatherHomeViewModel(weatherRepository)
    }

    @Test
    fun `when getWeather completes, it should emit success state`() = runTest {
        viewModel.uiState.test {

            assertEquals(WeatherHomeUiState(weather = fakeWeather), awaitItem())
        }
    }

    @Test
    fun `when getWeather completes, it should emit success state with humidity of 60`() = runTest {
        viewModel.uiState.test {

            assertEquals(WeatherHomeUiState(weather = fakeWeather), awaitItem())
            assertEquals(WeatherHomeUiState(weather = fakeWeather).weather?.humidity, 60)
        }
    }
}