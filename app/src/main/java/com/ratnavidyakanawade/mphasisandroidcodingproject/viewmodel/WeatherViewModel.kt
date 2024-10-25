package com.ratnavidyakanawade.mphasisandroidcodingproject.viewmodel

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ratnavidyakanawade.mphasisandroidcodingproject.BuildConfig
import com.ratnavidyakanawade.mphasisandroidcodingproject.DataStoreManager
import com.ratnavidyakanawade.mphasisandroidcodingproject.model.WeatherResponse
import com.ratnavidyakanawade.mphasisandroidcodingproject.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository, private val dataStoreManager: DataStoreManager
) : ViewModel() {
//    private val fusedLocationClient: FusedLocationProviderClient =
//        LocationServices.getFusedLocationProviderClient(context)
    var weatherState = mutableStateOf<WeatherResponse?>(null)
        private set

    var errorMessage = mutableStateOf("")
        private set

    //save last inserted city name in data store
    init {
        viewModelScope.launch {
            val lastCity = dataStoreManager.lastSearchedCity.first()
            if (!lastCity.isNullOrEmpty()) {
                fetchWeather(lastCity, BuildConfig.WEATHER_API_KEY)
            }
        }
    }

    //Method to fetch the weather data by passing city and apikey
    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                    val response = repository.getWeather(city, apiKey)
                    weatherState.value = response
                    dataStoreManager.saveCity(city)

            } catch (e: Exception) {
                errorMessage.value = "Error fetching weather data ${e.message}"
            }
        }
    }


    // Method to reset weatherState after navigating
    fun clearWeatherState() {
        weatherState.value = null
    }

    val lastCity: Flow<String?> = dataStoreManager.lastSearchedCity



}