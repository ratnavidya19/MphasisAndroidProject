package com.ratnavidyakanawade.mphasisandroidcodingproject.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ratnavidyakanawade.mphasisandroidcodingproject.screens.DetailScreen
import com.ratnavidyakanawade.mphasisandroidcodingproject.screens.MainScreen

@Composable
fun WeatherNavGraph() {
    val navController = rememberNavController()
    //defing navigation from mainscreen to details screen
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") { MainScreen(navController = navController) }
        composable(
            "detail_screen/{city}/{temperature}/{feelsLike}/{description}/{icon}",
            arguments = listOf(
                navArgument("city") { type = NavType.StringType },
                navArgument("temperature") { type = NavType.StringType },
                navArgument("feelsLike") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("icon") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val city = backStackEntry.arguments?.getString("city")
            val temperature = backStackEntry.arguments?.getString("temperature")
            val feelsLike = backStackEntry.arguments?.getString("feelsLike")
            val description = backStackEntry.arguments?.getString("description")
            val icon = backStackEntry.arguments?.getString("icon")

            Log.d("NavHostArguments", "City: $city, Temperature: $temperature, Feels Like: $feelsLike, Description: $description, Icon: $icon")

            DetailScreen(
                city = city ?: "N/A",
                temperature = temperature ?: "N/A",
                feelsLike = feelsLike ?: "N/A",
                description = description ?: "N/A",
                icon = icon ?: "N/A",
                navController = navController
            )
        }
    }
}

