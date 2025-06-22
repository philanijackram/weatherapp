package com.dvt.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.dvt.weatherapp.presentation.feature.home_screen.HomeScreen
import com.dvt.weatherapp.presentation.feature.home_screen.HomeScreenViewModel
import com.dvt.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: HomeScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        weatherData = viewModel.getWeatherData(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
