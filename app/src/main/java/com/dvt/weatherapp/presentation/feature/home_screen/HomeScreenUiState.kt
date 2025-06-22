package com.dvt.weatherapp.presentation.feature.home_screen

import com.dvt.weatherapp.domain.WeatherData

sealed class HomeScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val weatherData: WeatherData? = null
)