package com.ratnavidyakanawade.mphasisandroidcodingproject.repository

import com.ratnavidyakanawade.mphasisandroidcodingproject.model.WeatherResponse
import com.ratnavidyakanawade.mphasisandroidcodingproject.service.WeatherApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: WeatherApiService) {
    suspend fun getWeather(city: String, apiKey: String): WeatherResponse {
        return apiService.getWeatherByCity(city, apiKey)
    }
}