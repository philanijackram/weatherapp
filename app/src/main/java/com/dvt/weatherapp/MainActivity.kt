package com.dvt.weatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.dvt.weatherapp.location.LocationUtil
import com.dvt.weatherapp.presentation.feature.home_screen.HomeScreen
import com.dvt.weatherapp.presentation.feature.home_screen.HomeScreenViewModel
import com.dvt.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeScreenViewModel by viewModels()


    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fusedLocationProviderClient = LocationUtil.getLocationService(this)
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                viewModel.getWeatherDataFromApi(location.latitude, location.longitude)
            }
        }
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}