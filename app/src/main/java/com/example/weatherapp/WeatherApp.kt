package com.example.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}