package com.dvt.weatherapp.location

import com.dvt.weatherapp.MainActivity
import com.huawei.hms.location.FusedLocationProviderClient
import com.huawei.hms.location.LocationServices

object LocationUtil {
    fun getLocationService(activity: MainActivity): FusedLocationProviderClient{
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        return fusedLocationProviderClient
    }
}