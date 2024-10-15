package com.example.myweatherapp.data.repository

import com.example.myweatherapp.data.model.toWeather
import com.example.myweatherapp.data.network.WeatherApi
import com.example.myweatherapp.domain.model.Weather
import com.example.myweatherapp.common.Result
import com.example.myweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherRepository {
    override fun getWeatherForecast(city: String): Flow<Result<Weather>> = flow {
        emit(Result.Loading)
        try {
            val result = weatherApi.getWeatherForecast(city = city)
            emit(Result.Success(result.toWeather()))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.orEmpty()))
        } catch (exception: IOException) {
            emit(Result.Error("Please check your network connection and try again!"))
        }
    }.flowOn(dispatcher)
}
