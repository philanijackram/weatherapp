package com.dvt.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}