package com.dvt.weatherapp

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.viewModels
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.dvt.weatherapp.location.LocationUtil
import com.dvt.weatherapp.presentation.components.StandardDialog
import com.dvt.weatherapp.presentation.home_screen.HomeScreen
import com.dvt.weatherapp.presentation.home_screen.HomeScreenViewModel
import com.dvt.weatherapp.ui.theme.WeatherAppTheme
import com.dvt.weatherapp.util.LocationUtils
import com.dvt.weatherapp.util.LocationUtils.isLocationPermissionGranted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeScreenViewModel by viewModels()

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var isLocationPermissionGranted by remember {
                mutableStateOf(this.isLocationPermissionGranted())
            }

            val permissionLauncher = rememberLauncherForActivityResult(
                contract = RequestPermission(),
                onResult = { isGranted ->
                    isLocationPermissionGranted = isGranted
                }
            )

            LaunchedEffect(key1 = isLocationPermissionGranted) {
                if (!isLocationPermissionGranted) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                }
            }

            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var showDialog by remember {
                        mutableStateOf(false)
                    }
                    when {
                        isLocationPermissionGranted -> {
                            val fusedLocationProviderClient = LocationUtil.getLocationService(this)
                            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                                if (location != null) {
                                    viewModel.getWeatherDataFromApi(
                                        location.latitude,
                                        location.longitude
                                    )
                                    Log.d(
                                        "MainActivity",
                                        "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                                    )
                                }
                            }
                            HomeScreen(
                                viewModel = viewModel,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        ActivityCompat.shouldShowRequestPermissionRationale(
                            this, Manifest.permission.ACCESS_FINE_LOCATION
                        ) -> {
                            StandardDialog(
                                onPositiveButtonClick = {
                                    LocationUtils.openAppSettings(this)
                                    showDialog = false
                                },
                                onNegativeButtonClick = {
                                    showDialog = false
                                    this.finishAffinity()
                                }
                            )
                        }

                        else -> {
                            StandardDialog(
                                onPositiveButtonClick = {
                                    LocationUtils.openAppSettings(this)
                                    showDialog = false
                                },
                                onNegativeButtonClick = {
                                    showDialog = false
                                    this.finishAffinity()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}