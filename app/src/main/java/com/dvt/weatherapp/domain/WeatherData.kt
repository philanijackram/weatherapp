package com.dvt.weatherapp.domain

import com.dvt.weatherapp.R

data class WeatherData(
    val location: String,
    val currentDayImage: Int,
    val temperature: String,
    val days: List<DayData>
)

data class DayData(
    val day: String,
    val temperature: String,
    val weatherIcon: Int
)

object DummyData {
    val weatherData = WeatherData(
        location = "Ivory Park",
        currentDayImage = R.drawable.cloudy,
        temperature = "25°C",
        days = listOf(
            DayData(
                day = "Monday",
                temperature = "20°C",
                weatherIcon = R.drawable.property_120rainlight
            ),
            DayData(
                day = "Tuesday",
                temperature = "25°C",
                weatherIcon = R.drawable.property_110cloudynightlight
            ),
            DayData(
                day = "Wednesday",
                temperature = "22°C",
                weatherIcon = R.drawable.property_103_sunrise_light
            ),
            DayData(
                day = "Thursday",
                temperature = "28°C",
                weatherIcon = R.drawable.property_101_sun_light
            ),
            DayData(
                day = "Friday",
                temperature = "23°C",
                weatherIcon = R.drawable.property_120rainlight
            )
        )
    )
}