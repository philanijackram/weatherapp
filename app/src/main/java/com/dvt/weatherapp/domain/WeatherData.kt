package com.dvt.weatherapp.domain

data class WeatherData(
    val location: String,
    val temperature: String,
    val days:List<DayData>
)

data class DayData(
    val day: String,
    val temperature: String,
    val weatherIcon: String
)