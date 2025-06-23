package com.dvt.weatherapp.data.repository

import com.dvt.weatherapp.data.remote.WeatherApiService
import com.dvt.weatherapp.domain.WeatherDataDTO
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService
) {
    val apiKey = "5f64db54396ef36bd2d3076cc46a3b95"

    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        apiKey: String = this@WeatherRepository.apiKey
    ): Response<WeatherDataDTO> =
        weatherApiService.getWeatherForecast(
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        )

}