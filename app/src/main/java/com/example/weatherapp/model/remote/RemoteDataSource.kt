package com.example.weatherapp.model.remote


import com.example.weatherapp.util.BaseDataSource
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) :
    BaseDataSource() {

    suspend fun getWeatherByKey(lat: Double, lon: Double, exclude: String, appid: String) = getResult {
        apiService.getWeatherByKey(lat,lon,exclude,appid) }

}
