package com.example.weatherapp.model.remote

import com.example.weatherapp.model.entity.WeatherInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface NetworkApiService {

    @GET("onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}")
    suspend fun getWeatherByKey(
        @Path("lat") lat: Double,
        @Path("lon") lon : Double,
        @Path("exclude") exclude : String,
        @Path("appid") appid : String,

    ): Response<WeatherInfo>

}

