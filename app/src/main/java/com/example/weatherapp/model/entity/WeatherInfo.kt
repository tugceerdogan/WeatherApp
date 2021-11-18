package com.example.weatherapp.model.entity

data class WeatherInfo(
    val lon: Double,
    val lat: Double,
    val current: CurrentWeather,
)

data class CurrentWeather(
    val temp: Double,
    val weather: List<Weather>
)

data class Weather(
    val id: Int,
    val icon: String,
)