package com.dvt.weatherapp.data.repository

import com.dvt.weatherapp.data.local.WeatherDao
import com.dvt.weatherapp.data.local.WeatherEntity
import com.dvt.weatherapp.data.local.toEntity
import com.dvt.weatherapp.data.remote.WeatherApiService
import com.dvt.weatherapp.domain.WeatherDataDTO
import com.dvt.weatherapp.domain.CityDTO
import com.dvt.weatherapp.domain.DayDataDTO
import com.dvt.weatherapp.domain.CloudsDTO
import com.dvt.weatherapp.domain.CoordinatesDTO
import com.dvt.weatherapp.domain.MainDTO
import com.dvt.weatherapp.domain.SysDTO
import com.dvt.weatherapp.domain.WeatherDTO
import com.dvt.weatherapp.domain.WindDTO
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDao: WeatherDao
) {
    private val apiKey = "5f64db54396ef36bd2d3076cc46a3b95"

    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        apiKey: String = this.apiKey
    ): WeatherDataDTO {
        return try {
            val response = weatherApiService.getWeatherForecast(
                latitude = latitude,
                longitude = longitude,
                apiKey = apiKey
            )

            if (response.isSuccessful && response.body() != null) {
                val weatherData = response.body()!!

                val entities = weatherData.list.map { dayData ->
                    dayData.toEntity(weatherData.city)
                }

                weatherDao.clearWeather()
                weatherDao.insertWeatherList(entities)

                weatherData
            } else {
                fallbackToRoom()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            fallbackToRoom()
        }
    }

    private suspend fun fallbackToRoom(): WeatherDataDTO {
        val cached = weatherDao.getAllWeather()

        val dayDataList = cached.map { it.toDayDataDTO() }

        return WeatherDataDTO(
            city = CityDTO(
                id = 0,
                name = "Offline Mode",
                coordinates = CoordinatesDTO(0.0, 0.0),
                country = "N/A"
            ),
            cnt = dayDataList.size,
            cod = "local",
            list = dayDataList,
            message = 0
        )
    }


}

fun WeatherEntity.toDayDataDTO(): DayDataDTO {
    return DayDataDTO(
        dt = dateTime,
        main = MainDTO(
            temperature = temperature,
            feels_like = 0.0,
            temp_min = 0.0,
            temp_max = 0.0,
            pressure = 0,
            sea_level = 0,
            grnd_level = 0,
            humidity = humidity,
            temp_kf = 0.0
        ),
        weather = listOf(
            WeatherDTO(
                id = 0,
                main = "Cloudy",
                description = description,
                icon = icon
            )
        ),
        clouds = CloudsDTO(cloudiness),
        wind = WindDTO(speed = windSpeed, deg = windDegree, gust = 0.0),
        visibility = 0,
        pop = 0.0,
        sys = SysDTO("d"),
        dt_txt = dateText
    )
}