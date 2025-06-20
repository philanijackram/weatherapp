package com.dvt.weatherapp.data.remote

import com.dvt.weatherapp.domain.WeatherDataDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String
    ): Response<WeatherDataDTO>

}