package com.example.myweatherapp.data.repository

import com.example.myweatherapp.data.network.WeatherApi
import com.example.myweatherapp.domain.model.Weather
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import com.example.myweatherapp.data.sampleForecastResponse
import com.example.myweatherapp.common.Result
import com.example.myweatherapp.data.model.toWeather

import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WeatherRepositoryTest {
    private lateinit var repository: WeatherRepositoryImpl
    private val weatherApi = mockk<WeatherApi>()

    @Before
    fun setup() {
        repository = WeatherRepositoryImpl(weatherApi)
    }

    @Test
    fun `when getWeatherForecast is called, it should emit loading state and then success state`() =
        runTest {
            coEvery {
                weatherApi.getWeatherForecast(
                    any(),
                    any(),
                    any()
                )
            } returns sampleForecastResponse

            val results = mutableListOf<Result<Weather>>()
            repository.getWeatherForecast("Pune").collect { result ->
                results.add(result)
            }
            assertEquals(Result.Loading, results[0])

            assertEquals(Result.Success(sampleForecastResponse.toWeather()), results[1])
        }
}