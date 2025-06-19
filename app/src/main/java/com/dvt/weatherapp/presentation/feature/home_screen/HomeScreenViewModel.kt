package com.dvt.weatherapp.presentation.feature.home_screen

import androidx.lifecycle.ViewModel
import com.dvt.weatherapp.domain.DummyData
import com.dvt.weatherapp.domain.WeatherData

class HomeScreenViewModel : ViewModel() {

    fun getWeatherData(): WeatherData {
        return DummyData.weatherData
    }

}