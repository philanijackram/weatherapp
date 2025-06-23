package com.dvt.weatherapp.util

import com.dvt.weatherapp.R
import com.dvt.weatherapp.domain.DayDataDTO
import com.dvt.weatherapp.enums.WeatherCondition
import com.dvt.weatherapp.util.DateUtils.getDayName
import com.dvt.weatherapp.util.DateUtils.getLatestPerWeekday
import com.dvt.weatherapp.util.DateUtils.getTodayByName

object ResourceProvider {

    fun getImageIcon(icon: String): Int {
        return when (icon) {
            "01n" -> R.drawable._01n
            "01d" -> R.drawable._01d
            "03d", "03n" -> R.drawable._03d
            "04d", "04n" -> R.drawable._04d
            "09d", "09n" -> R.drawable._09d
            "10d", "10n" -> R.drawable._10d
            "11d", "11n" -> R.drawable._11d
            "13d", "13n" -> R.drawable._13d
            "50d", "50n" -> R.drawable._50d
            else -> {
                R.drawable._01n
            }
        }
    }

    fun getTodayWeatherConditionIcon(listDayData: List<DayDataDTO>): Int? {
        var weatherIcon: Int? = null
        getLatestPerWeekday(listDayData).forEach { dayData ->
            if (getDayName(dayData.dt_txt) == getTodayByName()) {
                weatherIcon = when (dayData.weather[0].main) {
                    WeatherCondition.CLOUDY.title -> R.drawable.cloudy
                    WeatherCondition.CLEAR.title -> R.drawable.forest
                    WeatherCondition.RAINY.title -> R.drawable.rainy
                    WeatherCondition.SUNNY.title -> R.drawable.sunny
                    else -> {
                        R.drawable.forest
                    }
                }
            }

        }
        return weatherIcon
    }
}