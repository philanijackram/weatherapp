@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.dvt.weatherapp.util

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {

    fun getDayName(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)

    }
}