package com.dvt.weatherapp.domain

import com.google.gson.annotations.SerializedName

data class WeatherDataDTO(
    val city: CityDTO,
    val cnt: Int,
    val cod: String,
    val list: List<DayDataDTO>,
    val message: Int
)

data class CityDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coordinates: CoordinatesDTO,
    @SerializedName("country")
    val country: String,
)

data class CoordinatesDTO(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

data class DayDataDTO(
    val dt: Long,
    val main: MainDTO,
    val weather: List<WeatherDTO>,
    val clouds: CloudsDTO,
    val wind: WindDTO,
    val visibility: Int,
    val pop: Double,
    val sys: SysDTO,
    val dt_txt: String

    )

data class MainDTO(
    @SerializedName("temp")
    val temperature: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Double
)

data class WeatherDTO(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class CloudsDTO(
    val all: Int
)

data class WindDTO(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class SysDTO(
    val pod: String
)