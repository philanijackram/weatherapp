package com.dvt.weatherapp.presentation.home_screen

import com.dvt.weatherapp.domain.WeatherDataDTO

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val weatherData: WeatherDataDTO? = null
)