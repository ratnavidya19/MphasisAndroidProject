package com.ratnavidyakanawade.mphasisandroidcodingproject.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.ratnavidyakanawade.mphasisandroidcodingproject.R

@Composable
fun DetailScreen(
    city: String,
    temperature: String,
    feelsLike: String,
    description: String,
    icon: String, navController: NavController
) {
    // Choose background color based on weather condition
    val backgroundColor = getBackgroundColorForCondition(description)



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Weather Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFF2196F3),
                contentColor = Color.White,
                elevation = 4.dp
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(padding)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // City Name and Weather Icon
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 8.dp,
                        backgroundColor = Color.White
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = city,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            GlideImage(
                                imageUrl = "https://openweathermap.org/img/wn/${icon}@2x.png",
                                contentDescription = "Weather Icon",
                                modifier = Modifier.size(100.dp)
                            )


                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Temperature Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 8.dp,
                        backgroundColor = Color.White
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Temperature: $temperature°C",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Feels Like: $feelsLike°C",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Gray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Weather Description Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 8.dp,
                        backgroundColor = Color.White
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Condition: $description",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun getBackgroundColorForCondition(description: String): Color {
    return when {
        description.contains("clear", ignoreCase = true) -> Color(0xFF87CEEB) // Light Sky Blue for Clear Sky
        description.contains("cloud", ignoreCase = true) -> Color(0xFFB0C4DE) // Light Steel Blue for Cloudy
        description.contains("rain", ignoreCase = true) -> Color(0xFF4682B4) // Steel Blue for Rain
        description.contains("snow", ignoreCase = true) -> Color(0xFFF0F8FF) // Alice Blue for Snow
        description.contains("thunderstorm", ignoreCase = true) -> Color(0xFF778899) // Light Slate Gray for Thunderstorm
        else -> Color(0xFFE0E0E0) // Default Light Gray
    }
}