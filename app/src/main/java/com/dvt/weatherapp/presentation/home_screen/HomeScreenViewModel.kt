package com.dvt.weatherapp.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dvt.weatherapp.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    fun getWeatherDataFromApi(
        latitude: Double,
        longitude: Double
    ) {

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val response = weatherRepository.getWeatherForecast(
                latitude = latitude,
                longitude = longitude
            )

            if (response.isSuccessful) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                val weatherDataDTO = response.body()
                if (weatherDataDTO != null) {
                    _uiState.value = _uiState.value.copy(weatherData = weatherDataDTO)
                }
            } else {
                _uiState.value = _uiState.value.copy(isLoading = false, error = response.message())
            }
        }
    }
}