package com.dvt.weatherapp.util

import com.dvt.weatherapp.R

object ResourceProvider {
    fun getImageIcon(icon: String): Int {
        return when (icon) {
            "01n" -> R.drawable.property_103_sunrise_light
            else -> R.drawable.property_110cloudynightlight
        }
    }
}