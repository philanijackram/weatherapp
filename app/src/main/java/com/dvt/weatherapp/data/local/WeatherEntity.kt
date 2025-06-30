package com.dvt.weatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dvt.weatherapp.domain.CityDTO
import com.dvt.weatherapp.domain.DayDataDTO

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val dateTime: Long, // use `dt` as unique key
    val cityId: Int,
    val cityName: String,
    val country: String,
    val temperature: Double,
    val description: String,
    val icon: String,
    val windSpeed: Double,
    val windDegree: Int,
    val cloudiness: Int,
    val humidity: Int,
    val dateText: String
)

fun DayDataDTO.toEntity(city: CityDTO): WeatherEntity {
    return WeatherEntity(
        dateTime = dt,
        cityId = city.id,
        cityName = city.name,
        country = city.country,
        temperature = main.temperature,
        description = weather.firstOrNull()?.description ?: "",
        icon = weather.firstOrNull()?.icon ?: "",
        windSpeed = wind.speed,
        windDegree = wind.deg,
        cloudiness = clouds.all,
        humidity = main.humidity,
        dateText = dt_txt
    )
}
