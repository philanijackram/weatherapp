package com.dvt.weatherapp.location

import com.dvt.weatherapp.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object LocationUtil {
    fun getLocationService(activity: MainActivity): FusedLocationProviderClient{
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        return fusedLocationProviderClient
    }
}