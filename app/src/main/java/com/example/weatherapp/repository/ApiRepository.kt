package com.example.weatherapp.repository

import com.example.weatherapp.model.remote.RemoteDataSource
import com.example.weatherapp.util.performNetworkOperation
import javax.inject.Inject


class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
) {

    fun enter(lat: Double, lon: Double, exclude: String, appid: String) = performNetworkOperation{
        remoteDataSource.getWeatherByKey(lat, lon,exclude,appid)
        }
}
