package com.dvt.weatherapp.util

import com.dvt.weatherapp.domain.DayDataDTO
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {

    fun getDayName(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }

    fun getTodayByName(): String {
        val formatter = DateTimeFormatter.ofPattern("EEEE")
        val today = LocalDateTime.now()
        return today.format(formatter)
    }

    fun getLatestPerWeekday(listDayData: List<DayDataDTO>): List<DayDataDTO> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        val latestMap = mutableMapOf<String, DayDataDTO>()

        for (weatherData in listDayData) {

            val day = getDayName(weatherData.dt_txt)

            val existing = latestMap[day]

            if (existing == null ||
                LocalDateTime.parse(existing.dt_txt, formatter)
                    .isBefore(LocalDateTime.parse(weatherData.dt_txt, formatter))
            ) {
                latestMap[day] = weatherData
            }
        }

        return latestMap.values.toList()
    }

}