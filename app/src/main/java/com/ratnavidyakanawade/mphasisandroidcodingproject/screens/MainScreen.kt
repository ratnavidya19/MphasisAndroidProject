package com.ratnavidyakanawade.mphasisandroidcodingproject.screens

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.ratnavidyakanawade.mphasisandroidcodingproject.viewmodel.WeatherViewModel
import androidx.compose.runtime.getValue  // Add this
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ratnavidyakanawade.mphasisandroidcodingproject.BuildConfig


@Composable
fun MainScreen(viewModel: WeatherViewModel = hiltViewModel(), navController: NavHostController) {
    val weatherState by viewModel.weatherState
    val errorMessage by viewModel.errorMessage
    val context = LocalContext.current


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var city by remember { mutableStateOf("") }

        TextField(
            value = city,
            onValueChange = { city = it },
            label = {
                Text("Enter city name")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (city.isNotEmpty()) {
                viewModel.fetchWeather(city, BuildConfig.WEATHER_API_KEY)
            }
        }) {
            Text("Get Weather")
        }

        Spacer(modifier = Modifier.height(16.dp))

        weatherState?.let { weather ->
            // Navigate to detail screen and pass data
            if (weather.name != null) {
               Log.d("weatherState", ""+weather.main.temp);
                navController.navigate(
                    "detail_screen/${weather.name}/${weather.main.temp}/${weather.main.feels_like}/${weather.weather[0].description}/${weather.weather[0].icon}"
                ) {
                    popUpTo("main_screen") { inclusive = false }
                }
                viewModel.clearWeatherState()
            }
        }

        // Check for weather data
//        weatherState?.let { state ->
//            Text(text = "City: ${state.name ?: "N/A"}")
//            Text(text = "Temperature: ${state.main?.temp ?: "N/A"}°C")
//            Text(text = "Feels like: ${state.main?.feels_like ?: "N/A"}°C")
//            Text(text = "Description: ${state.weather.firstOrNull()?.description ?: "N/A"}")
//
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data("https://openweathermap.org/img/wn/${state.weather[0].icon}@2x.png")
//                    .crossfade(true)
//                    .build(),
////                placeholder = painterResource(R.drawable.ic_launcher_background),
////                error = painterResource(R.drawable.ic_launcher_background),
//                contentDescription = "Weather Icon",
//                modifier = Modifier.size(64.dp),
//                contentScale = ContentScale.Fit
//            )
//        }

        // Display error message if exists
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }
    }


}


