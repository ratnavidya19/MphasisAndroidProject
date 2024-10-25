package com.ratnavidyakanawade.mphasisandroidcodingproject.screenTest

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.ratnavidyakanawade.mphasisandroidcodingproject.screens.DetailScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//@RunWith(JUnit4::class)
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Composable
    @Test
    fun displayWeatherDetails_andNavigateBack() {
        val navController = rememberNavController()
        composeTestRule.setContent {
            DetailScreen(
                city = "New York",
                temperature = "20",
                feelsLike = "18",
                description = "Clear Sky",
                icon = "01d",
                navController = navController
            )
        }

        // Check if the city name is displayed
        composeTestRule.onNodeWithText("Weather details for New York").assertExists()

        // Click back button
        composeTestRule.onNodeWithText("Back").performClick()

        // Verify that we return to MainScreen
        composeTestRule.onNodeWithText("Enter city name").assertExists()
    }
}