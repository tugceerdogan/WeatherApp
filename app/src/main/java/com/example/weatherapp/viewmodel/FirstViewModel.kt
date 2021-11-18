package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.weatherapp.model.entity.WeatherInfo
import com.example.weatherapp.repository.ApiRepository
import com.example.weatherapp.util.Resource
import javax.inject.Inject


@HiltViewModel
class FirstViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun enter(latitude : Double, longitude : Double, exclude : String, appid: String) : LiveData<Resource<WeatherInfo>> {
        return apiRepository.enter(latitude,longitude,exclude, appid)
    }
}
