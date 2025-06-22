package com.dvt.weatherapp.data.repository

import com.dvt.weatherapp.data.remote.WeatherApiService
import com.dvt.weatherapp.domain.WeatherDataDTO
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService
) {

    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): Response<WeatherDataDTO> =
        weatherApiService.getWeatherForecast(
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        )

}